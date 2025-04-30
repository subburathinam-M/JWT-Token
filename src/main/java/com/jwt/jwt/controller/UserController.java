package com.jwt.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwt.entity.Users;
import com.jwt.jwt.response.ApiResponse;
import com.jwt.jwt.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/allusers")
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        List<Users> usersList = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(usersList));
    }

    @GetMapping("/hello")
    public String hello() {
    return "Hello, world!";
}


}
