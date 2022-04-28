package com.example.nosql.mongo.web;

import com.example.nosql.mongo.domain.application.UserService;
import com.example.nosql.mongo.domain.model.Article;
import com.example.nosql.mongo.domain.model.User;
import com.example.nosql.mongo.domain.model.createArticleDTO;
import com.example.nosql.mongo.domain.model.createUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody createUserDTO userDTO){
        User user=userService.newUser(userDTO.username(),userDTO.password(),userDTO.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
