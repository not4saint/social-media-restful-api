package com.projects.notasaint.socialmediaRESTAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private User userId;

    @Enumerated
    private RoleValue role;
}
