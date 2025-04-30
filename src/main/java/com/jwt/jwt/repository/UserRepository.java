package com.jwt.jwt.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.jwt.entity.Users;


@Repository
public interface UserRepository extends MongoRepository<Users, String> {

@Query("{ 'username': ?0 }")
Optional<Users> findByUsername(String username);

@Query("{ 'email': ?0 }") // 🔁 changed from 'username'
Optional<Users> findByEmail(String email);



}
