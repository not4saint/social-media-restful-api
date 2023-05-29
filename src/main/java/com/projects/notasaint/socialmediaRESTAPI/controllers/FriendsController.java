package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.services.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendService friendService;

    @GetMapping("/friends")
    public List<FriendDTO> getAllFriendsAtUser() {
        return friendService.findAllFriendsAtUserInCurrentAuth();
    }
}
