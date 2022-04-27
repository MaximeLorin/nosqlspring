package com.example.nosql.mongo.domain.repositories;

import com.example.nosql.mongo.domain.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Repository
public interface ArticlesRepository extends CrudRepository<Article, String> {
//    List<Article> findByTitle(String title);
//
    @Query(nativeQuery = true, value = "SELECT * FROM articles a WHERE SIMILARITY(title,?1) > 0.2")
    List<Article> findByTitle(String title);


}
