package com.example.noSql.mongo.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Articles")
public class Article {
    @Id
    private String id;
    private String title;
    private String summary;
    private String content;

    public Article(String id, String title, String summary, String content) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
