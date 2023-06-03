package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.FriendService;
import com.projects.notasaint.socialmediaRESTAPI.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class RelationshipController {
    private final FriendService friendService;
    private final AuthUtil authUtil;

    @GetMapping("/friends")
    public List<FriendDTO> getAllFriendsAtUser() {
        final User user = authUtil.getAuthenticate();
        return friendService.findAllFriendsAtUserInCurrentAuth(user, user.getEmail());
    }

    @PostMapping("/add-friend")
    public ResponseEntity<HttpStatus> sendRequestToFriends(@RequestBody FriendDTO friendDTO) {
        final User user = authUtil.getAuthenticate();
        friendService.addFriendInCurrentAuth(user, friendDTO, user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete-friend")
    public ResponseEntity<HttpStatus> deleteFriend(@RequestBody FriendDTO friendDTO) {
        final User user = authUtil.getAuthenticate();
        friendService.deleteFriendInCurrentAuth(user, friendDTO, user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
