package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class LoginDTO {
    @Email
    String email;

    @NotEmpty
    char[] password;
}
