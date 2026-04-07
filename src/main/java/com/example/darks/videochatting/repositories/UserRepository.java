package com.example.darks.videochatting.repositories;

import com.example.darks.videochatting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
