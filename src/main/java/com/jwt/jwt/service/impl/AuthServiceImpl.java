package com.jwt.jwt.service.impl;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.jwt.dto.LoginRequest;
import com.jwt.jwt.entity.Users;
import com.jwt.jwt.exception.CustomException;
import com.jwt.jwt.exception.UnauthorizedException;
import com.jwt.jwt.repository.UserRepository;
import com.jwt.jwt.response.TokenResponse;
import com.jwt.jwt.service.AuthService;
import com.jwt.jwt.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;


@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Users register(Users user) {
        Optional<Users> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new CustomException("Username already exists!");
        }

    // Encrypt the password before saving
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
    }

    // @Override
    // public String login(LoginRequest loginRequest) {
    //     Optional<Users> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
    //     System.out.println(optionalUser);
    //     if (optionalUser.isPresent()) {
    //         Users dbUser = optionalUser.get();
    //         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //         if (passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
    //             return jwtUtils.generateToken(dbUser.getEmail(), dbUser.getRole());
    //         } else {
    //             throw new RuntimeException("Invalid password");
    //         }
    //     } else {
    //         throw new RuntimeException("User not found with email: " + loginRequest.getEmail());
    //     }
    // }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Optional<Users> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid credentials"); // Generic message for security
        }
        
        Users dbUser = user.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (!encoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials"); // Same message as above
        }
        
        // Generate both tokens
        return new TokenResponse(
            jwtUtils.generateToken(dbUser.getEmail(), dbUser.getRole()),
            jwtUtils.generateRefreshToken(dbUser.getEmail(), dbUser.getRole())
        );
    }
    
    @Override
    public TokenResponse refreshToken(String refreshToken) {
        try {
            // First validate the token structure and signature
            if (!jwtUtils.validateToken(refreshToken)) {
                throw new RuntimeException("Invalid token");
            }
    
            Claims claims = jwtUtils.extractClaims(refreshToken);
            
            // Check if it's a refresh token
            if (!"refresh".equals(claims.get("tokenType"))) {
                throw new RuntimeException("Invalid token type");
            }
            
            // Verify the user still exists and is active
            String email = claims.getSubject();
            Users user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Use current role from database, not from token
            return new TokenResponse(
                jwtUtils.generateToken(email, user.getRole()),
                jwtUtils.generateRefreshToken(email, user.getRole()) // Rotating refresh token
            );
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Refresh token expired");
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid refresh token");
        }
    }

}