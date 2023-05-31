package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    ResponsePostDTO convertPostToPostDTO(Post post);
}
