package com.example.darks.videochatting.ws_security;

import com.example.darks.videochatting.models.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPrincipal implements StompPrincipal {

    private final User user;

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public String getId() {
        return user.getId().toString();
    }
}
