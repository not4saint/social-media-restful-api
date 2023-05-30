package com.projects.notasaint.socialmediaRESTAPI.services;

import com.projects.notasaint.socialmediaRESTAPI.dto.PostDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.PostNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.mappers.PostMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import com.projects.notasaint.socialmediaRESTAPI.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public PostDTO findPostByUserLoginAndPostId(String login, int id) {
        Optional<Post> optionalPost = postRepository.findPostById(id);

        if (optionalPost.isEmpty())
            throw new PostNotFoundException("Post not found");

        PostDTO postDTO = postMapper.convertPostToPostDTO(optionalPost.get());
        postDTO.setLogin(login);
        return postDTO;
    }
}
