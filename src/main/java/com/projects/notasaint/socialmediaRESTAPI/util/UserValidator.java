package com.projects.notasaint.socialmediaRESTAPI.util;

import com.projects.notasaint.socialmediaRESTAPI.dto.RegisterDTO;
import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserRepository userRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegisterDTO.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        RegisterDTO user = (RegisterDTO) target;

        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent())
            errors.rejectValue("email", "", "User already registered");
    }
}
