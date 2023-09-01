package com.github.atipera.service;

import com.github.atipera.exception.GithubException;
import com.github.atipera.model.Repository;

import java.util.List;

public interface RepositoryService {

    List<Repository> listRepositories(String username) throws GithubException;
}
