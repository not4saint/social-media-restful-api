package com.projects.notasaint.socialmediaRESTAPI.models;

import com.projects.notasaint.socialmediaRESTAPI.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;

    private String surname;

    @NotEmpty(message = "Login should not be empty")
    private String login;

    @Column(name = "phone_number")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Incorrect format of the entered number")
    private String phoneNumber;

    @Email(message = "Incorrect format of the entered email")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Column(name = "date_of_birthday")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "non_locked")
    private boolean nonLocked;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany(mappedBy = "owner")
    private Set<Dialog> ownerDialogs;

//    @OneToMany(mappedBy = "companion")
//    private Set<Dialog> companionDialogs;

    @OneToMany(mappedBy = "firstUser")
    private Set<Relationship> users;

//    @OneToMany(mappedBy = "secondUser")
//    private Set<Relationship> friends;
}
