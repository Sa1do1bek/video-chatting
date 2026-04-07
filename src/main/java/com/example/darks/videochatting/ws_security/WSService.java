package com.example.darks.videochatting.ws_security;

import com.example.darks.videochatting.dtos.UserInfoWS;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class WSService {

    @Value("${ws.secret}")
    private String secretKey;

    @Value("${ws.expiration}")
    private String expiration;

    public String generateWSToken(UUID userId, String username) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(expiration)))
                .signWith(
                        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))
                )
                .compact();
    }

    public UserInfoWS validateAndGetUserInfo(String token) {

        if (token == null || token.isBlank()) return null;
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            UUID userId = UUID.fromString(claims.getSubject());
            String username = claims.get("username", String.class);

            return new UserInfoWS(userId, username);

        } catch (JwtException | IllegalArgumentException ex) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }
    }
}
