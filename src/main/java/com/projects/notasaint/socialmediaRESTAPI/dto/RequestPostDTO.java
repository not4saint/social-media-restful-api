package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class RequestPostDTO {
    private String heading;

    @NotEmpty
    private String text;
}
