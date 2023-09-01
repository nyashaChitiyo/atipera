package com.github.atipera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private int status;
}
