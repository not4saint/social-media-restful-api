package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.LoginDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponseToken;
import com.projects.notasaint.socialmediaRESTAPI.mappers.UserMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Role;
import com.projects.notasaint.socialmediaRESTAPI.models.RoleValue;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.repositories.UserRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.AuthService;
import com.projects.notasaint.socialmediaRESTAPI.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    public ResponseToken login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        try {
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials!");
        }

        return new ResponseToken(jwtUtil.generateToken(loginDTO.getEmail()));
    }

    @Override
    @Transactional
    public void register(RegisterDTO registerDTO) {
        // TODO: 01.06.2023 check user exists
        User user = userMapper.convertRegisterDTOToUser(registerDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setNonLocked(true);
        userRepository.save(user);

        Role role = new Role(user, RoleValue.USER);
        user.setRole(role);

        userRepository.save(user);
    }
}
