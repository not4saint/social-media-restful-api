package com.projects.notasaint.socialmediaRESTAPI.mappers;

import com.projects.notasaint.socialmediaRESTAPI.dto.PostDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    PostDTO convertPostToPostDTO(Post post);
}
