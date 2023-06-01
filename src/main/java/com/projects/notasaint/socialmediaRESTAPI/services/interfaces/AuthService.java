package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;

import com.projects.notasaint.socialmediaRESTAPI.dto.LoginDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponseToken;

public interface AuthService {
    ResponseToken login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);
}
