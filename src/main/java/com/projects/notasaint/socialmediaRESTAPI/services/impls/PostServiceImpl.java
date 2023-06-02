package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.PostNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.mappers.PostMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.repositories.PostRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.PostService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j2
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public ResponsePostDTO findPostByUserLoginAndPostId(String login, long id) {
        Optional<Post> optionalPost = postRepository.findPostById(id);

        if (optionalPost.isEmpty()) {
            log.error("Post not found");
            throw new PostNotFoundException("Post not found");
        }

        ResponsePostDTO responsePostDTO = postMapper.convertPostToPostDTO(optionalPost.get());
        responsePostDTO.setLogin(login);
        return responsePostDTO;
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void addPostToUser(RequestPostDTO requestPostDTO, User user, String email) {
        Post post = new Post(requestPostDTO.getHeading(), requestPostDTO.getText(), LocalDateTime.now(), user);

        postRepository.save(post);
        user.getPostList().add(post);
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void updatePostToUserById(RequestPostDTO requestPostDTO, long postId, String email) {
        Post post = findPostById(postId);

        post.setHeading(requestPostDTO.getHeading());
        post.setText(requestPostDTO.getText());
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);
        post.getUser().getPostList().add(post);
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void deletePostToUserById(long postId, String email) {
        postRepository.delete(findPostById(postId));
    }

    private Post findPostById(long postId) {
        Optional<Post> optionalPost = postRepository.findPostById(postId);

        if (optionalPost.isEmpty()) {
            log.error("Post does not exist");
            throw new PostNotFoundException("Post not found");
        }
        return optionalPost.get();
    }
}
