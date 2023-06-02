package com.projects.notasaint.socialmediaRESTAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;

    private String surname;

    @NotEmpty(message = "Login should not be empty")
    private String login;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Incorrect format of the entered number")
    private String phoneNumber;

    @Email(message = "Incorrect format of the entered email")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private char[] password;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    private boolean nonLocked;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> postList;

    @ManyToMany
    @JoinTable(name = "User_Friendship",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "friend", referencedColumnName = "id", nullable = false))
    private List<User> friends;
}
