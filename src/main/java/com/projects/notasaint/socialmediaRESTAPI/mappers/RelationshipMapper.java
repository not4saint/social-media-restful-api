package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface RelationshipMapper {
    FriendDTO convertUserToFriendDTO(User user);
}
