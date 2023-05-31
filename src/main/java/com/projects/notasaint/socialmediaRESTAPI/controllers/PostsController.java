package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class PostsController {
    private final PostService postService;

    @GetMapping("/{login}/{postId}")
    public ResponsePostDTO getPost(@PathVariable String login, @PathVariable int postId) {
        return postService.findPostByUserLoginAndPostId(login, postId);
    }

    @PostMapping("/add-post")
    public ResponseEntity<HttpStatus> createPost(@RequestBody RequestPostDTO requestPostDTO) {
        postService.addPostToUser(requestPostDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{postId}/update-post")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody RequestPostDTO requestPostDTO,
                                                 @PathVariable long postId) {
        postService.updatePostToUserById(requestPostDTO, postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable long postId) {
        postService.deletePostToUserById(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
