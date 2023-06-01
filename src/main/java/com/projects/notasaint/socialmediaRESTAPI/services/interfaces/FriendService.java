package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;

import java.util.List;

public interface FriendService {
    List<FriendDTO> findAllFriendsAtUserInCurrentAuth();
}
