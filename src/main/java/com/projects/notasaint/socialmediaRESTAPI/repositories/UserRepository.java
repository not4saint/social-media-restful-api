package com.projects.notasaint.socialmediaRESTAPI.repositories;

import com.projects.notasaint.socialmediaRESTAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserByEmail(String email);
}
