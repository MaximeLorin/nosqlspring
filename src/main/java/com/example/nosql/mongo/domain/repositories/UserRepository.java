package com.example.nosql.mongo.domain.repositories;

import com.example.nosql.mongo.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
