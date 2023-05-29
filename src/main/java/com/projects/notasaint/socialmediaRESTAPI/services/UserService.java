package com.projects.notasaint.socialmediaRESTAPI.services;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findUserByLogin(String login);
}
