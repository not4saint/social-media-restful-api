package com.projects.notasaint.socialmediaRESTAPI.exceptions;

public class UserAlreadyRegisteredWithSuchEmailException extends RuntimeException {
    public UserAlreadyRegisteredWithSuchEmailException(String message) {
        super(message);
    }
}
