package com.example.darks.videochatting.services.user_service;

import com.example.darks.videochatting.dtos.auth.RegisterRequest;
import com.example.darks.videochatting.dtos.auth.RegisterResponse;
import com.example.darks.videochatting.dtos.base.ApiResponse;
import com.example.darks.videochatting.models.User;
import com.example.darks.videochatting.repositories.UserRepository;
import com.example.darks.videochatting.ws_security.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final WSService wsService;
    private final UserRepository userRepository;

    @Override
    public ApiResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());

        User savedUser = userRepository.save(user);

        return new ApiResponse(
                new RegisterResponse(
                        savedUser.getId(), savedUser.getUsername(),
                        wsService.generateWSToken(savedUser.getId(), savedUser.getUsername())),
                "User Created");
    }
}
