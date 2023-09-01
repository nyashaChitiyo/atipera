package com.github.atipera.controller;

import com.github.atipera.exception.GithubException;
import com.github.atipera.model.Repository;
import com.github.atipera.service.RepositoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.NotAcceptableStatusException;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/list/repos")
public class RepositoryController {

    private final RestTemplate restTemplate;
    private final RepositoryService repositoryService;

    public RepositoryController(RestTemplate restTemplate, RepositoryService repositoryService) {
        this.restTemplate = restTemplate;
        this.repositoryService = repositoryService;
    }

    @GetMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repository> repo(@PathVariable String username) throws GithubException {
        return repositoryService.listRepositories(username);
    }
}
