package com.example.darks.videochatting.services;

import com.example.darks.videochatting.dtos.MeetingCancelllationResponse;
import com.example.darks.videochatting.dtos.meeting.InvitationResponse;
import com.example.darks.videochatting.dtos.meeting.InviteRequest;
import com.example.darks.videochatting.dtos.meeting.MeetingAcceptanceResponse;
import com.example.darks.videochatting.dtos.meeting.MeetingRejectionResponse;
import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.exceptions.MeetingNotFoundException;
import com.example.darks.videochatting.models.Meeting;
import com.example.darks.videochatting.services.meeting_service.IMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JaaService {

    @Value("${app.id}")
    private String appId;

    private final IMeetingService meetingService;

    public ApiResponse createMeeting(UUID inviterId, InviteRequest request) {
        Meeting meeting = meetingService.createMeeting(inviterId, request.receiverId());

        System.out.println(appId + meeting.getRoomId());
        return new ApiResponse(new InvitationResponse(meeting.getInviter()), "An invitation for a meeting!");
    }

    public MeetingAcceptanceResponse acceptMeeting(UUID accepterId) {
        Meeting meeting = meetingService.getMeetingByReceiverId(accepterId);

        return new MeetingAcceptanceResponse(meeting.getInviter().getId(), meeting.getReceiver().getId(), meeting.getRoomId().toString());
    }

    public MeetingRejectionResponse rejectMeeting(UUID rejecterId) {
        Meeting meeting;
        try {
            meeting = meetingService.getMeetingByReceiverId(rejecterId);
        } catch (Exception e) {
            meeting = null;
        }

        if (meeting != null) {
            meetingService.deleteMeeting(meeting.getId());
            return new MeetingRejectionResponse(meeting.getInviter().getId());
        }
        else
            throw new MeetingNotFoundException("Meeting not found!");
    }

    public MeetingCancelllationResponse cancelMeeting(UUID cancellerId) {
        Meeting meeting;
        try {
            meeting = meetingService.getMeetingByReceiverId(cancellerId);
        } catch (Exception e) {
            meeting = null;
        }

        if (meeting != null) {
            meetingService.deleteMeeting(meeting.getId());
            return new MeetingCancelllationResponse(meeting.getReceiver().getId());
        }
        else
            throw new MeetingNotFoundException("Meeting not found!");
    }
}
