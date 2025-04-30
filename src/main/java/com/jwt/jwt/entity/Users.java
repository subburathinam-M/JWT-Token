package com.jwt.jwt.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Users {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;

}
