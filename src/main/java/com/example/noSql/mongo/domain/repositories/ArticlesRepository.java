package com.example.noSql.mongo.domain.repositories;

import com.example.noSql.mongo.domain.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ArticlesRepository extends MongoRepository<Article,String> {
    List<Article> findByTitle(String title);

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    List<Article> findByTitlePart(String titlePart);
}
