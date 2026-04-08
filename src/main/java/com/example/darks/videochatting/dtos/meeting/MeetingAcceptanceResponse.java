package com.example.darks.videochatting.dtos.meeting;

import java.util.UUID;

public record MeetingAcceptanceResponse(
        UUID inviterId,
        UUID accepterId,
        String room
) {
}
