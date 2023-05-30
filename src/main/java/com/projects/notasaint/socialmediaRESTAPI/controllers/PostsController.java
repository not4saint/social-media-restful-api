package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.PostDTO;
import com.projects.notasaint.socialmediaRESTAPI.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class PostsController {
    private final PostService postService;

    @GetMapping("/{login}/{id}")
    public PostDTO getPost(@PathVariable String login, @PathVariable int id) {
        return postService.findPostByUserLoginAndPostId(login, id);
    }
}
