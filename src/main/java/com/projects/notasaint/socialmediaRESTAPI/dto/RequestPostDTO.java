package com.projects.notasaint.socialmediaRESTAPI.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestPostDTO {
    @Size(max = 50, message = "Heading's size should be less than 50")
    private String heading;

    @Size(max = 1000, message = "Text's size should be less than 1000")
    private String text;
}
