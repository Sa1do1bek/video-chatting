package com.example.darks.videochatting.repositories;

import com.example.darks.videochatting.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
    Optional<Meeting> findByReceiver_Id(UUID receiverId);
}
