package com.projects.notasaint.socialmediaRESTAPI.models;

import com.projects.notasaint.socialmediaRESTAPI.models.enums.RoleValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Users userId;

    @Enumerated(EnumType.STRING)
    private RoleValue role;

    public Role(Users userId, RoleValue role) {
        this.userId = userId;
        this.role = role;
    }
}
