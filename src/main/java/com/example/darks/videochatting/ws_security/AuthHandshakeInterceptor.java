//package com.example.darks.videochatting.ws_security;
//
//import com.example.darks.videochatting.dtos.UserInfoWS;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.Map;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class AuthHandshakeInterceptor implements HandshakeInterceptor {
//
//    private final WSService wsService;
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request,
//                                   ServerHttpResponse response,
//                                   WebSocketHandler wsHandler,
//                                   Map<String, Object> attributes) {
//
//        if (!(request instanceof ServletServerHttpRequest servletRequest)) {
//            return false;
//        }
//
//        HttpServletRequest servlet = servletRequest.getServletRequest();
//        String token = servlet.getParameter("token");
//
//        if (token == null) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return false;
//        }
//
//        UserInfoWS userInfo;
//
//        try {
//            userInfo = wsService.validateAndGetUserInfo(token);
//        } catch (Exception e) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return false;
//        }
//
//        UUID userId = userInfo.userId();
//        String username = userInfo.username();
//
////        boolean valid;
////
////        if (role == SessionRole.HOST) {
////            valid = userService.existsById(userId);
////        } else if (role == SessionRole.PLAYER) {
////            valid = playerRepository.existsById(userId);
////        } else {
////            valid = false;
////        }
////
////        if (!valid) {
////            response.setStatusCode(HttpStatus.UNAUTHORIZED);
////            return false;
////        }
//
//        attributes.put("userId", userId);
//        attributes.put("username", username);
//
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request,
//                               ServerHttpResponse response,
//                               WebSocketHandler wsHandler,
//                               Exception exception) {}
//}