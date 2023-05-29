package com.projects.notasaint.socialmediaRESTAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private User mainUser;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private User friendId;
}
