package com.projects.notasaint.socialmediaRESTAPI.repositories;

import com.projects.notasaint.socialmediaRESTAPI.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
