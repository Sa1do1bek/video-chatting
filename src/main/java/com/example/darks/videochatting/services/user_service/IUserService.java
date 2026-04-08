package com.example.darks.videochatting.services.user_service;

import com.example.darks.videochatting.dtos.auth.RegisterRequest;
import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.models.User;

import java.util.UUID;

public interface IUserService {
    ApiResponse register(RegisterRequest request);
    User getUserById(UUID userId);
}
