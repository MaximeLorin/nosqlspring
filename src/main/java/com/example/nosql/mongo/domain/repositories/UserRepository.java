package com.example.nosql.mongo.domain.repositories;

import com.example.nosql.mongo.domain.model.Article;
import com.example.nosql.mongo.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
