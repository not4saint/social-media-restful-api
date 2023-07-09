package com.projects.notasaint.socialmediaRESTAPI.models;

import com.projects.notasaint.socialmediaRESTAPI.models.enums.RelationshipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "first_user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Users firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Users secondUser;

    @Column(name = "relationship_type")
    @Enumerated(EnumType.STRING)
    private RelationshipType relationshipType;
}
