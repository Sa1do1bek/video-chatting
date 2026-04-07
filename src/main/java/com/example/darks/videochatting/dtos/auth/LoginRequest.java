package com.example.darks.videochatting.dtos.auth;

public record LoginRequest(
        String email,
        String password
) {
}
