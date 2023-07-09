package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.UserNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.mappers.UserMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.repositories.UserRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO findUserByLogin(String login) {
        Optional<Users> optionalUser = userRepository.findUserByLogin(login);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User not found");
        return userMapper.convertUserToUserDTO(optionalUser.get());
    }
}
