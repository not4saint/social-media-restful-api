package com.projects.notasaint.socialmediaRESTAPI.dto;

import lombok.Value;

@Value
public class RegisterDTO {
    String firstname;
    String surname;
    String email;
    char[] password;
}
