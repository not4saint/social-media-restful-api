package com.projects.notasaint.socialmediaRESTAPI.dto;

import com.projects.notasaint.socialmediaRESTAPI.models.Gender;
import com.projects.notasaint.socialmediaRESTAPI.models.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
    @NotEmpty
    private String firstname;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String login;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phoneNumber;

    @Email
    private String email;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Set<Post> postList;
}
