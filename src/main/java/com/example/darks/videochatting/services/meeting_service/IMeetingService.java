package com.example.darks.videochatting.services.meeting_service;

import com.example.darks.videochatting.models.Meeting;

import java.util.UUID;

public interface IMeetingService {
    Meeting getMeetingByReceiverId(UUID accepterId);
    Meeting createMeeting(UUID inviterId, UUID receiverId);
    void deleteMeeting(UUID meetingId);
}
