package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.exceptions.ExceptionResponse;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.PostNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleUserOrPostNotFoundException(RuntimeException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(request.getRequestURI(), e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(RuntimeException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(request.getRequestURI(), e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    public String configureMessage(BindingResult bindingResult) {
        StringBuilder str = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError fieldError : errors) {
            str.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";");
        }
        return str.toString();
    }
}
