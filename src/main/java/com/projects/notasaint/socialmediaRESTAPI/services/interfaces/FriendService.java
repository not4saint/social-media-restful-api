package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;

import java.util.List;

public interface FriendService {
    List<FriendDTO> findAllFriendsAtUserInCurrentAuth(User user, String email);

    void addFriendInCurrentAuth(User user, FriendDTO friendDTO, String email);

    void deleteFriendInCurrentAuth(User user, FriendDTO friendDTO, String email);
}
