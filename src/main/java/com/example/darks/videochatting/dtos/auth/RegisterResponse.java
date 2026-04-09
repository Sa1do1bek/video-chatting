package com.example.darks.videochatting.dtos.auth;

import java.util.UUID;

public record RegisterResponse(
        UUID userId,
        String username
//        String ws_token
) {
}
