package com.projects.notasaint.socialmediaRESTAPI.services;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;

import java.util.List;

public interface FriendService {
    List<FriendDTO> findAllFriendsAtUserInCurrentAuth();
}
