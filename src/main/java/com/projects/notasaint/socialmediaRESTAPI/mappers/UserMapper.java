package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.UserDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO convertUserToUserDTO(Users users);
    Users convertRegisterDTOToUser(RegisterDTO registerDTO);
}
