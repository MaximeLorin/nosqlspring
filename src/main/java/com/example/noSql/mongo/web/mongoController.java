package com.example.noSql.mongo.web;

import com.example.noSql.mongo.domain.application.ArticleService;
import com.example.noSql.mongo.domain.model.Article;
import com.example.noSql.mongo.domain.model.ChangeArticleContentDTO;
import com.example.noSql.mongo.domain.model.createArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class mongoController {
    @Autowired
    public ArticleService articleService;

    @PostMapping("/articles")
    ResponseEntity<Article> createArticle(@RequestBody createArticleDTO articleDTO){
        Article article=articleService.newArticle(articleDTO.title(),articleDTO.summary(),articleDTO.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/articles")
    List<Article> getEveryArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    ResponseEntity<Article> getById(@PathVariable String id){
        return articleService.getArticleById(id).map(u -> ResponseEntity.ok(u)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/articles/search")
    List<Article> getByTitle(@RequestParam("title") String title){
        return articleService.getArticleByTitle(title);
    }
    @GetMapping("/articles/searchPart")
    List<Article> getByTitlePart(@RequestParam("title") String titlePart){
        return articleService.getArticleByTitlePart(titlePart);
    }
    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteByid(@PathVariable String id){
        articleService.deleteOneArticle(id);
    }

    @PatchMapping("/articles/{id}")
    ResponseEntity<Article> changeContent(@PathVariable String id, @RequestBody ChangeArticleContentDTO changeArticleContentDTO){
        try{
            Article changedArticle= articleService.changeContent(id,changeArticleContentDTO.content());
            return ResponseEntity.status(HttpStatus.CREATED).body(changedArticle);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }}
}
