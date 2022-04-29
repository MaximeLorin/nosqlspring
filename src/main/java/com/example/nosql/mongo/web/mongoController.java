package com.example.nosql.mongo.web;

import com.example.nosql.mongo.domain.application.ArticleService;
import com.example.nosql.mongo.domain.model.Article;
import com.example.nosql.mongo.domain.model.ChangeArticleContentDTO;
import com.example.nosql.mongo.domain.model.User;
import com.example.nosql.mongo.domain.model.createArticleDTO;
import com.example.nosql.mongo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class mongoController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/articles")
    ResponseEntity<Article> createArticle(@RequestBody createArticleDTO articleDTO){
        Optional<User> user = this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.isPresent()){

            Article article=articleService.newArticle(articleDTO.title(),articleDTO.image(),articleDTO.summary(),articleDTO.content(),articleDTO.draft(),user.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }

        return null;
    }

    @GetMapping("/articles")
    List<Article> getEveryArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    ResponseEntity<Article> getById(@PathVariable String id){
        return articleService.getArticleById(id).map(u -> ResponseEntity.ok(u)).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping("/articles/search")
//    List<Article> getByTitle(@RequestParam("title") String title){
//        return articleService.getArticleByTitle(title);
//    }
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
