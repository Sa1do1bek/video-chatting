package com.example.darks.videochatting.controllers;

import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.services.JaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/meetings")
@RequiredArgsConstructor
public class VideoChattingController {

    private final JaaService jaaService;

    @PostMapping()
    public ResponseEntity<ApiResponse> startMeeting() {
        return ResponseEntity.ok(jaaService.createMeeting());
    }

//    @MessageMapping("/session/{sessionId}/answer")
//    public void receiveAnswer(@DestinationVariable UUID sessionId,
//                              @Payload CreatePlayerAnswerRequest request,
//                              Principal principal) {
//        try {
//            PlayerAnswerResponse response = webSocketService.submitAnswer(sessionId, request);
//
//            messagingTemplate.convertAndSendToUser(
//                    principal.getName(),
//                    "/queue/feedback",
//                    response
//            );
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}