package com.example.nosql.mongo.domain.application;

import com.example.nosql.mongo.domain.model.Article;
import com.example.nosql.mongo.domain.repositories.ArticlesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ArticleService {
    public final ArticlesRepository articlesRepository;

//    @Autowired
    public ArticleService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    public Article newArticle(String title,String image,String summary, String content,boolean draft){
        Article article = new Article(UUID.randomUUID().toString(),title,image,summary,content,draft);
        articlesRepository.save(article);
        return article;
    }

    public List<Article> getAllArticles(){
        return (List<Article>) articlesRepository.findAll();
    }

    public Optional<Article> getArticleById(String id){
        return articlesRepository.findById(id);
    }
//    public List<Article> getArticleByTitle(String title){
//        return articlesRepository.findByTitle(title);
//    }
    public List<Article> getArticleByTitlePart(String titlePart){

        return articlesRepository.findByTitle(titlePart);
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
