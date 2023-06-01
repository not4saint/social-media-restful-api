package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO convertUserToUserDTO(User user);
    User convertRegisterDTOToUser(RegisterDTO registerDTO);
}
