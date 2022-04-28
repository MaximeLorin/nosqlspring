package com.example.nosql.mongo.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Access(AccessType.FIELD)
public class Article {
    @Id
    private String id;
    @Column
    private String image;
    @Column
    private String title;
    @Column
    private String summary;
    @Column
    private String content;
    private boolean draft;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Article(String id, String title,String image, String summary, String content,boolean draft) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.summary = summary;
        this.content = content;
        this.draft = draft;
    }
    protected Article(){

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }
}
