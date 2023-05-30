package com.projects.notasaint.socialmediaRESTAPI.dto;

import com.projects.notasaint.socialmediaRESTAPI.models.Comment;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
public class PostDTO {
    @NotEmpty
    private String login;

    @NotEmpty
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;
}
