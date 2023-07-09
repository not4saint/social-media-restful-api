package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;

import java.util.List;

public interface FriendService {
    List<FriendDTO> findAllFriendsAtUserInCurrentAuth(Users users, String email);

    void addFriendInCurrentAuth(Users users, FriendDTO friendDTO, String email);

    void deleteFriendInCurrentAuth(Users users, FriendDTO friendDTO, String email);
}
