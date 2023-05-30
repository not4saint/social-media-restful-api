package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentDTO {
    @NotEmpty
    private String login;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String text;
}
