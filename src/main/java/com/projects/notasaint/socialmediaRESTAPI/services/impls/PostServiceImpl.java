package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.RequestPostDTO;
import com.projects.notasaint.socialmediaRESTAPI.dto.ResponsePostDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.FileUploadedException;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.PostNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.mappers.PostMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.repositories.PostRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Value("${file.path_to_all_files}")
    private String filePath;
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
//    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void addPostToUser(RequestPostDTO requestPostDTO, Users users, String email, MultipartFile file) {
        log.info("Yser");

        Post post = Post.builder()
                .text(requestPostDTO.getText())
                .heading(requestPostDTO.getHeading())
                .createdAt(LocalDateTime.now())
                .user(users)
                .imagePath(!file.isEmpty() ? uploadFileAndGetFilename(file) : null)
                .build();

        postRepository.save(post);
        users.getPosts().add(post);
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void updatePostToUserById(RequestPostDTO requestPostDTO, long postId, String email, MultipartFile file) {
        Post post = findPostById(postId);
        if (!file.isEmpty()) {
            String fileName = uploadFileAndGetFilename(file);
            post.setImagePath(fileName);
        }
        
        post.setHeading(requestPostDTO.getHeading());
        post.setText(requestPostDTO.getText());

        postRepository.save(post);
        post.getUser().getPosts().add(post);
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

    // Создаем уникальное название для файла и загружаем файл
    private String uploadFileAndGetFilename(MultipartFile file) {
        String curDate = LocalDateTime.now().toString();
        String fileName = filePath + "/" + "post_" + curDate + "_" +
                Objects.requireNonNull(file.getOriginalFilename()).toLowerCase()
                        .replaceAll(" ", "-");
        try {
            file.transferTo(new File(fileName));
        } catch (Exception e) {
            throw new FileUploadedException("The file was not uploaded");
        }
        return fileName;
    }
}
