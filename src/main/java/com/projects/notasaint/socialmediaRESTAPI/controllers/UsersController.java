package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/{login}")
    public UserDTO getUserById(@PathVariable String login) {
        return userService.findUserByLogin(login);
    }
}
