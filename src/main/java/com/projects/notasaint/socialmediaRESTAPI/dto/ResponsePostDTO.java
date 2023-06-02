package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
public class ResponsePostDTO {
    @NotEmpty
    private String login;

    private String heading;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    private String imagePath;
}
