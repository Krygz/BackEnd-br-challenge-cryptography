package com.test.cryptography.controller.exceptions;

import com.test.cryptography.service.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> notFound(ResourceNotFound exception , HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Resource Not Found");
        error.setMessage(exception.getMessage());
        error.setStatus(status.value());
        error.setPath(request.getRequestURI());
        return new ResponseEntity<StandardError>(error , HttpStatus.NOT_FOUND);
    }
}
