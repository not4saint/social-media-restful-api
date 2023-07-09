package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PostMapper {
    ResponsePostDTO convertPostToPostDTO(Post post);
}
