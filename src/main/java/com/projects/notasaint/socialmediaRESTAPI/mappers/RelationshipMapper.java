package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelationshipMapper {
    FriendDTO convertUserToFriendDTO(Users users);
}
