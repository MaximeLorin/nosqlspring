package com.example.noSql.mongo.domain.repositories;

import com.example.noSql.mongo.domain.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticlesRepository extends MongoRepository<Article,String> {

}
