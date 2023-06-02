package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class LoginDTO {
    @Email(message = "Incorrect format of the entered email")
    String email;

    @NotEmpty(message = "Firstname should not be empty")
    char[] password;
}
