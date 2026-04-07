package com.example.darks.videochatting.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "username")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

//    @Column(nullable = false, unique = true)
//    private String email;

    @Column(nullable = false, unique = true)
    private String username;
//    private String password;
//
//    private boolean isModerator;
//
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//    private LocalDateTime lastActivityTime;

}
