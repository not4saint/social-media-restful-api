package com.projects.notasaint.socialmediaRESTAPI.services.interfaces;


import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    ResponsePostDTO findPostByUserLoginAndPostId(String login, long id);

    void addPostToUser(RequestPostDTO requestPostDTO, Users users, String email, MultipartFile file);

    void updatePostToUserById(RequestPostDTO requestPostDTO, long postId, String email, MultipartFile file);

    void deletePostToUserById(long postId, String email);
}
