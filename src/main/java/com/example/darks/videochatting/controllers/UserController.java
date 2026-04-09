package com.example.darks.videochatting.controllers;

import com.example.darks.videochatting.dtos.auth.RegisterRequest;
import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.services.user_service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    public  ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
