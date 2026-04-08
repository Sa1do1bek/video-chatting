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
        name = "meetings",
        indexes = {
                @Index(name = "idx_username", columnList = "username")
        }
)
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "inviter_id", nullable = false)
    private User inviter;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;

    private UUID roomId;
}
