package com.projects.notasaint.socialmediaRESTAPI.repositories;

import com.projects.notasaint.socialmediaRESTAPI.models.Relationship;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.models.enums.RelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
    @Query("select r from Relationship r where (r.firstUser=?1 or r.secondUser=?1) and r.relationshipType=?2")
    List<Relationship> findAllByMainUserOrSecondUserAndRelationshipType(Users users, RelationshipType relationshipType);
    @Query("select r from Relationship r where (r.firstUser=?1 and r.secondUser=?2) or r.firstUser=?2 and r.secondUser=?1")
    Optional<Relationship> findRelationshipByFirstAndSecondUser(Users firstUsers, Users secondUsers);
}
