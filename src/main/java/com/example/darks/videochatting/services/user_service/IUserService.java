package com.example.darks.videochatting.services.user_service;

import com.example.darks.videochatting.dtos.auth.RegisterRequest;
import com.example.darks.videochatting.dtos.base.ApiResponse;

public interface IUserService {
    ApiResponse register(RegisterRequest request);
}
