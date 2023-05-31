package com.projects.notasaint.socialmediaRESTAPI.services;


import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;

public interface PostService {
    ResponsePostDTO findPostByUserLoginAndPostId(String login, long id);

    void addPostToUser(RequestPostDTO requestPostDTO);

    void updatePostToUserById(RequestPostDTO requestPostDTO, long postId);

    void deletePostToUserById(long postId);
}
