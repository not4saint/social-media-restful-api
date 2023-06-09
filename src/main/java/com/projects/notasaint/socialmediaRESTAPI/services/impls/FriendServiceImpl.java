package com.projects.notasaint.socialmediaRESTAPI.services.impls;

import com.projects.notasaint.socialmediaRESTAPI.dto.FriendDTO;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.UserOperationsFriendException;
import com.projects.notasaint.socialmediaRESTAPI.exceptions.UserNotFoundException;
import com.projects.notasaint.socialmediaRESTAPI.mappers.RelationshipMapper;
import com.projects.notasaint.socialmediaRESTAPI.models.Relationship;
import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import com.projects.notasaint.socialmediaRESTAPI.models.enums.RelationshipType;
import com.projects.notasaint.socialmediaRESTAPI.repositories.RelationshipRepository;
import com.projects.notasaint.socialmediaRESTAPI.repositories.UserRepository;
import com.projects.notasaint.socialmediaRESTAPI.services.interfaces.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final RelationshipRepository relationshipRepository;
    private final UserRepository userRepository;
    private final RelationshipMapper relationshipMapper;
    @Override
    @PreAuthorize("#email == authentication.principal.username")
    public List<FriendDTO> findAllFriendsAtUserInCurrentAuth(Users users, String email) {
        List<FriendDTO> friends =
                relationshipRepository.findAllByMainUserOrSecondUserAndRelationshipType(users, RelationshipType.FRIENDS)
                .stream()
                .map(fr -> relationshipMapper.convertUserToFriendDTO(fr.getFirstUser() == users ? fr.getFirstUser() : fr.getSecondUser()))
                .toList();
        if (friends.isEmpty())
            return Collections.emptyList();

        return friends;
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void addFriendInCurrentAuth(Users users, FriendDTO friendDTO, String email) {
        if (users.getLogin().equals(friendDTO.getLogin()))
            throw new UserOperationsFriendException("The user cannot add himself");

        Users friend = userRepository.findUserByLogin(friendDTO.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Relationship> optionalRelationship = relationshipRepository.findRelationshipByFirstAndSecondUser(users, friend);

        if (optionalRelationship.isEmpty()) {
            relationshipRepository.save(Relationship.builder()
                    .firstUser(users)
                    .secondUser(friend)
                    .relationshipType(RelationshipType.FIRST_FOLLOWER)
                    .build());
        } else {
            optionalRelationship.get().setRelationshipType(RelationshipType.FRIENDS);
        }
    }

    @Override
    @PreAuthorize("#email == authentication.principal.username")
    @Transactional
    public void deleteFriendInCurrentAuth(Users users, FriendDTO friendDTO, String email) {
        if (users.getLogin().equals(friendDTO.getLogin()))
            throw new UserOperationsFriendException("The user cannot delete himself");

        Users friend = userRepository.findUserByLogin(friendDTO.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Relationship> optionalRelationship = relationshipRepository.findRelationshipByFirstAndSecondUser(users, friend);

        if (optionalRelationship.isEmpty()) {
            throw new UserOperationsFriendException("Users are not friends");
        } else {
            Relationship relationship = optionalRelationship.get();
            if (relationship.getRelationshipType() == RelationshipType.FRIENDS) {
                relationship.setRelationshipType(RelationshipType.SECOND_FOLLOWER);
                relationshipRepository.save(relationship);
            } else if (relationship.getRelationshipType() == RelationshipType.FIRST_FOLLOWER) {
                relationshipRepository.delete(relationship);
            } else {
                throw new UserOperationsFriendException("It is impossible to unsubscribe because the user has not been subscribed");
            }
        }
    }
}
