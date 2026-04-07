package com.example.darks.videochatting.services;

import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.dtos.video_chatting.JaaSTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JaaService {

    @Value("${jitsi.meet.url}")
    private String url;

    @Value("${app.id}")
    private String appId;

    public ApiResponse createMeeting() {
        String roomId = UUID.randomUUID().toString();
        System.out.println(url + appId + roomId);
        return new ApiResponse(new JaaSTokenResponse(url + roomId, roomId), "created");
    }
}
