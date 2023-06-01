package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;

import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;


public interface UserService {
    UserDTO findUserByLogin(String login);
}
