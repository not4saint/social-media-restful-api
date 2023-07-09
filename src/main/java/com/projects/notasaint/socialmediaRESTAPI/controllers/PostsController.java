package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.IncorrectSizePostFieldsException;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.PostService;
import com.projects.notasaint.socialmediaRESTAPI.util.AuthUtil;
import com.projects.notasaint.socialmediaRESTAPI.util.ExceptionUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class PostsController {
    private final PostService postService;
    private final AuthUtil authUtil;

    @GetMapping("/post/{login}/{postId}")
    public ResponsePostDTO getPost(@PathVariable String login, @PathVariable int postId) {
        return postService.findPostByUserLoginAndPostId(login, postId);
    }

    @PostMapping(name = "/add-post", produces = "application/json")
    public ResponseEntity<HttpStatus> createPost(@RequestBody @Valid RequestPostDTO requestPostDTO,
                                                 @RequestPart(required = false) MultipartFile file,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new IncorrectSizePostFieldsException(ExceptionUtil.configureMessage(bindingResult));

        final Users users = authUtil.getAuthenticate();
        postService.addPostToUser(requestPostDTO, users, users.getEmail(), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/update-post/{postId}")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody @Valid RequestPostDTO requestPostDTO,
                                                 @PathVariable long postId,
                                                 @RequestPart(required = false) MultipartFile file,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new IncorrectSizePostFieldsException(ExceptionUtil.configureMessage(bindingResult));

        final Users users = authUtil.getAuthenticate();
        postService.updatePostToUserById(requestPostDTO, postId, users.getEmail(), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable long postId) {
        final Users users = authUtil.getAuthenticate();
        postService.deletePostToUserById(postId, users.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
