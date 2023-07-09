package com.projects.notasaint.socialmediaRESTAPI.repositories;

import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findUserByLogin(String login);
    Optional<Users> findUserByEmail(String email);
}
