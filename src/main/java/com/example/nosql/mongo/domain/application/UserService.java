package com.example.nosql.mongo.domain.application;

import com.example.nosql.mongo.domain.model.Article;
import com.example.nosql.mongo.domain.model.User;
import com.example.nosql.mongo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User newUser(String username, String password, String email){
        User user = new User(UUID.randomUUID().toString(),username,passwordEncoder.encode(password),email);
        userRepository.save(user);
        return user;
    }
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
