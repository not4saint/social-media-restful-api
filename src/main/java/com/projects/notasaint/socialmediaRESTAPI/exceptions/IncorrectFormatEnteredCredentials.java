package com.projects.notasaint.socialmediaRESTAPI.exceptions;

public class IncorrectFormatEnteredCredentials extends RuntimeException {
    public IncorrectFormatEnteredCredentials(String message) {
        super(message);
    }
}
