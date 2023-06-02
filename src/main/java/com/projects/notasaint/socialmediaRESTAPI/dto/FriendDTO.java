package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FriendDTO {
    @NotEmpty(message = "Firstname should not be empty")
    private String firstname;

    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @NotEmpty(message = "Login should not be empty")
    private String login;
}
