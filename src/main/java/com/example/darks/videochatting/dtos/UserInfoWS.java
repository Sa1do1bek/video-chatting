package com.example.darks.videochatting.dtos;

import java.util.UUID;

public record UserInfoWS(
        UUID userId,
        String username
) {
}
