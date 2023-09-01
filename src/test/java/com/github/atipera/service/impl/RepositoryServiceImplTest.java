package com.github.atipera.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceImplTest {

    @InjectMocks
    RepositoryServiceImpl repositoryService;

    @Test
    public void testWhenUsernameIsNull(){
        assertThrows(IllegalArgumentException.class, () -> repositoryService.listRepositories(null));
    }
}