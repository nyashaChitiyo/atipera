package com.github.atipera.exception;

import com.github.atipera.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.NotAcceptableStatusException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {GithubException.class})
    public ResponseEntity<Object> handleUserNotFoundException(HttpStatusCodeException e){
        var exceptionResponse = e.getResponseBodyAs(ExceptionResponse.class);
        String message = exceptionResponse.message();
        ExceptionResponse updatedExceptionResponse = new ExceptionResponse(message, e.getStatusCode().value());
        return new ResponseEntity<>(updatedExceptionResponse,e.getStatusCode());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity handleXMLException(HttpMediaTypeNotAcceptableException e){
        String message = e.getMessage().replace('"',' ');
        ExceptionResponse updatedExceptionResponse = new ExceptionResponse(message, e.getStatusCode().value());
        return ResponseEntity.status(e.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(updatedExceptionResponse);
    }
}
