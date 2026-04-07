package com.example.darks.videochatting.dtos.video_chatting;

public record ChatUserRequest(
        Long id,
        String username,
        String email,
        String avatarUrl,
        boolean isModerator
) {
}
