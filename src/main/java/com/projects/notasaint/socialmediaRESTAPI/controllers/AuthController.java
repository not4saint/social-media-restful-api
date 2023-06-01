package com.projects.notasaint.socialmediaRESTAPI.controllers;

import com.projects.notasaint.socialmediaRESTAPI.dto.LoginDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponseToken;
import com.projects.notasaint.socialmediaRESTAPI.services.AuthService;
import com.projects.notasaint.socialmediaRESTAPI.util.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social-media")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserValidator userValidator;

    @PostMapping("/login")
    public ResponseToken performLogin(@RequestBody @Valid LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        userValidator.validate(registerDTO, bindingResult);
        authService.register(registerDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
