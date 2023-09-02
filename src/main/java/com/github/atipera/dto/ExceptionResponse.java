package com.github.atipera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

public record ExceptionResponse(String message,int status) {
}
