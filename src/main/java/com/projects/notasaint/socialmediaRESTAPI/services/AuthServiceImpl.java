package com.projects.notasaint.socialmediaRESTAPI.services;

import com.projects.notasaint.socialmediaRESTAPI.dto.LoginDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseToken login(LoginDTO loginDTO) {

        return null;
    }

    @Override
    public void register(RegisterDTO registerDTO) {

    }
}
