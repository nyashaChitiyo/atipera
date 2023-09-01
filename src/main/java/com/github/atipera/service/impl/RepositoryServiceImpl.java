package com.github.atipera.service.impl;

import com.github.atipera.exception.GithubException;
import com.github.atipera.model.Branch;
import com.github.atipera.model.Repository;
import com.github.atipera.service.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final RestTemplate restTemplate;
    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${tailUrl}")
    private String tailUrl;
    public RepositoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Repository> listRepositories(String username) throws GithubException {
        if(username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username cannot be null");
        var url = baseUrl+username+tailUrl;
        ResponseEntity<List<Repository>> repoExchange;
        List<Repository> repositories = null;
        try{
            repoExchange = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Repository>>() {
                    });

            repositories = repoExchange.getBody();
        }
        catch (HttpStatusCodeException e){
            throw new GithubException(e);
        }
        var filteredRepositories = filterForkRepository(repositories);
        return getListOfBranches(filteredRepositories);
    }

    private List<Repository> filterForkRepository(List<Repository> repositories){
        return repositories.stream().filter(a -> !a.isFork()).toList();
    }

    private List<Repository> getListOfBranches(List<Repository> repositories){
        for(Repository repository : repositories){

            String branchUrl = repository.getBranches_url();
            branchUrl = branchUrl.replace("{/branch}","");
            ResponseEntity<List<Branch>> branchExchange = restTemplate.exchange(branchUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Branch>>() {
                    });
            List<Branch> branches = branchExchange.getBody();
            repository.setBranches(branches);
        }
        return repositories;
    }
}
