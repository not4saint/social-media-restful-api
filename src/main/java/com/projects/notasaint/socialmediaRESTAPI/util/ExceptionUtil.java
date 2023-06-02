package com.projects.notasaint.socialmediaRESTAPI.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExceptionUtil {
    public static String configureMessage(BindingResult bindingResult) {
        StringBuilder str = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError fieldError : errors) {
            str.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";\n");
        }
        return str.toString();
    }
}
