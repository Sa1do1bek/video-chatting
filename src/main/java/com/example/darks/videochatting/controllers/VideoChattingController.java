package com.example.darks.videochatting.controllers;

import com.example.darks.videochatting.dtos.MeetingCancelllationResponse;
import com.example.darks.videochatting.dtos.meeting.InviteRequest;
import com.example.darks.videochatting.dtos.meeting.MeetingAcceptanceResponse;
import com.example.darks.videochatting.dtos.meeting.MeetingRejectionResponse;
import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.services.JaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VideoChattingController {

    private final JaaService jaaService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/calls/invite/{userId}")
    public void inviteUser(@DestinationVariable UUID userId,
                              @Payload InviteRequest request) {
        try {

            ApiResponse response = jaaService.createMeeting(userId, request);

            messagingTemplate.convertAndSendToUser(
                    String.valueOf(request.receiverId()),
                    "/queue/feedback",
                    response
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/calls/accept/{accepterId}")
    public void acceptMeeting(@DestinationVariable UUID accepterId) {
        MeetingAcceptanceResponse response = jaaService.acceptMeeting(accepterId);
        ApiResponse apiResponse = new ApiResponse(response.room(), "Room for a meeting!");

        messagingTemplate.convertAndSendToUser(
                String.valueOf(response.inviterId()),
                "/queue/feedback",
                apiResponse
        );

        messagingTemplate.convertAndSendToUser(
                String.valueOf(response.accepterId()),
                "/queue/feedback",
                apiResponse
        );
    }

    @MessageMapping("/calls/reject/{rejecterId}")
    public void rejectMeeting(@DestinationVariable UUID rejecterId) {
        MeetingRejectionResponse response = jaaService.rejectMeeting(rejecterId);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(response.inviterId()),
                "/queue/feedback",
                new ApiResponse(null, "A meeting invitation has been rejected!")
        );
    }

    @MessageMapping("/calls/cancel/{rejecterId}")
    public void cancelMeeting(@DestinationVariable UUID cancellerId) {
        MeetingCancelllationResponse response = jaaService.cancelMeeting(cancellerId);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(response.receiverId()),
                "/queue/feedback",
                new ApiResponse(null, "A meeting invitation has been cancelled!")
        );
    }
}