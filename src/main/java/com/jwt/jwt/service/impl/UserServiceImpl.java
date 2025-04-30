package com.jwt.jwt.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jwt.jwt.entity.Users;
import com.jwt.jwt.repository.UserRepository;
import com.jwt.jwt.service.UserService;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


}
