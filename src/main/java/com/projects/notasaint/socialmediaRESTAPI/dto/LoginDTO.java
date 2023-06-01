package com.projects.notasaint.socialmediaRESTAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class LoginDTO {
    String email;
    char[] password;
}
