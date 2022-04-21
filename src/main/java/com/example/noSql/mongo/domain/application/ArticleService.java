package com.example.noSql.mongo.domain.application;

import com.example.noSql.mongo.domain.model.Article;
import com.example.noSql.mongo.domain.repositories.ArticlesRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ArticleService {
    public ArticlesRepository articlesRepository;


    public ArticleService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    public Article newArticle(String title, String summary, String content){
        Article article = new Article(UUID.randomUUID().toString(),title,summary,content);
        articlesRepository.save(article);
        return article;
    }

    public List<Article> getAllArticles(){
        return articlesRepository.findAll();
    }

    public Optional<Article> getArticleById(String id){
        return articlesRepository.findById(id);
    }

    public void deleteOneArticle(String id){
        articlesRepository.deleteById(id);
    }

    public Article changeContent(String id, String newContent) throws Exception {
        Article article=articlesRepository.findById(id).orElseThrow(Exception::new);

        article.setContent(newContent);
        articlesRepository.save(article);
        return article;
    }
}