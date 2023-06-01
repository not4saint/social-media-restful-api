package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
public class ResponsePostDTO {
    @NotEmpty
    private String login;

    private String heading;

    @NotEmpty
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
