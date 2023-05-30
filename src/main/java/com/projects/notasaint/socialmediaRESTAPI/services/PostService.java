package com.projects.notasaint.socialmediaRESTAPI.services;


import com.projects.notasaint.socialmediaRESTAPI.dto.PostDTO;

public interface PostService {
    PostDTO findPostByUserLoginAndPostId(String login, int id);
}
