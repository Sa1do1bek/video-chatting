package com.example.darks.videochatting.dtos.meeting;

import com.example.darks.videochatting.models.User;

public record InvitationResponse(
        User inviter
) {
}
