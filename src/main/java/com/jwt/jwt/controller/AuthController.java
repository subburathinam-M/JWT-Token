package com.jwt.jwt.controller;

import com.jwt.jwt.dto.LoginRequest;
import com.jwt.jwt.entity.Users;
import com.jwt.jwt.response.ApiResponse;
import com.jwt.jwt.response.TokenResponse;
import com.jwt.jwt.service.AuthService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController 
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    // @SecurityRequirement(name = "bearerAuth") // 🔐 This endpoint requires JWT token
    public ResponseEntity<ApiResponse<Users>> saveUser(@RequestBody Users user) {
        Users saveUsers = authService.register(user);
        return ResponseEntity.ok(ApiResponse.success(saveUsers));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest request) {
        TokenResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        TokenResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
