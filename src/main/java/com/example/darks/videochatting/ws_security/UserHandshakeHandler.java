package com.example.darks.videochatting.ws_security;

import com.example.darks.videochatting.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    private final UserRepository userRepository;

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        String userIdStr = (String) attributes.get("userId");
        String role = (String) attributes.get("username");

        if (userIdStr == null || role == null)
            return null;

        UUID userId = UUID.fromString(userIdStr);

        return userRepository.findById(userId)
                .map(UserPrincipal::new)
                .orElse(null);
    }
}

