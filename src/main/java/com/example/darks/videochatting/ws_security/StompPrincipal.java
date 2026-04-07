package com.example.darks.videochatting.ws_security;

import java.security.Principal;

public interface StompPrincipal extends Principal {
    String getId();
}
