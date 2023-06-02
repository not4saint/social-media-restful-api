package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class RegisterDTO {
    @NotEmpty(message = "Login should not be empty")
    String login;

    @Email(message = "Incorrect format of the entered email")
    String email;

    @NotEmpty(message = "Password should not be empty")
    char[] password;
}
