package com.projects.notasaint.socialmediaRESTAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dialog_id", referencedColumnName = "id")
    private Dialog dialog;

    @NotEmpty
    private String text;

    private boolean isReading;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime localDateTime;
}
