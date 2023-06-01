package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class RegisterDTO {
    @NotEmpty
    String firstname;

    @NotEmpty
    String surname;

    @Email
    String email;

    @NotEmpty
    char[] password;
}
