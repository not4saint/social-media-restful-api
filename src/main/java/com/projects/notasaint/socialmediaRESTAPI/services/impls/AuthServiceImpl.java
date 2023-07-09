package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.LoginDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponseToken;
import com.projects.notasaint.socialmediaRESTAPI.mappers.UserMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Role;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.models.enums.RoleValue;
import com.projects.notasaint.socialmediaRESTAPI.repositories.RoleRepository;
import com.projects.notasaint.socialmediaRESTAPI.repositories.UserRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.AuthService;
import com.projects.notasaint.socialmediaRESTAPI.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseToken login(LoginDTO loginDTO) {
        log.info(passwordEncoder.encode(loginDTO.getPassword()));
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
        Users user = userMapper.convertRegisterDTOToUser(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setNonLocked(true);
        userRepository.save(user);

        Role role = new Role(user, RoleValue.USER);
        roleRepository.save(role);
        user.setRole(role);

        userRepository.save(user);
    }
}
