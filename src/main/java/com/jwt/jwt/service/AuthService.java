package com.jwt.jwt.service;

import com.jwt.jwt.dto.LoginRequest;
import com.jwt.jwt.entity.Users;
import com.jwt.jwt.response.TokenResponse;

public interface AuthService {

    Users register(Users user);

    TokenResponse login(LoginRequest loginRequest);

    TokenResponse refreshToken(String refreshToken); // New method
    

}
