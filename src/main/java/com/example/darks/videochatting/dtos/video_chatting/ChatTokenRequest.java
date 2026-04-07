package com.example.darks.videochatting.dtos.video_chatting;

import lombok.NonNull;

public record ChatTokenRequest(
        @NonNull
        String roomName,
        ChatUserRequest user
) {
}
