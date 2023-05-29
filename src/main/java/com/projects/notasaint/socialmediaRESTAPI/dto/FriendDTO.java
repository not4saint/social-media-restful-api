package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FriendDTO {
    @NotEmpty
    private String firstname;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String login;
}
