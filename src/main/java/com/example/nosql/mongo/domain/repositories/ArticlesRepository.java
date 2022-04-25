package com.example.nosql.mongo.domain.repositories;

import com.example.nosql.mongo.domain.model.Article;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface ArticlesRepository extends CrudRepository<Article, String> {
//    List<Article> findByTitle(String title);
//
////    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
//    List<Article> findByTitlePart(String titlePart);
}
