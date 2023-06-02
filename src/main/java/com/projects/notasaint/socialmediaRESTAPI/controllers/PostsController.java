package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.PostService;
import com.projects.notasaint.socialmediaRESTAPI.util.AuthUtil;
import jakarta.validation.Valid;
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
        final User user = AuthUtil.getAuthenticate();
        postService.addPostToUser(requestPostDTO, user, user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/update-post/{postId}")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody RequestPostDTO requestPostDTO,
                                                 @PathVariable long postId) {
        final User user = AuthUtil.getAuthenticate();
        postService.updatePostToUserById(requestPostDTO, postId, user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable long postId) {
        final User user = AuthUtil.getAuthenticate();
        postService.deletePostToUserById(postId, user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
