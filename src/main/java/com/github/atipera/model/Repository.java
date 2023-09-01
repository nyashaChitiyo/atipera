package com.github.atipera.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Repository {

    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean fork;
    private Owner owner;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String branches_url;
    private List<Branch> branches;
}
