package com.example.th2de2;

import java.io.Serializable;

/**
 * @author tuanpham
 * @since 4/20/2023
 */
public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private String scope;
    private String subject;
    private float rating;

    public Book(int id, String name, String author, String scope, String subject, float rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.scope = scope;
        this.subject = subject;
        this.rating = rating;
    }

    public Book(String name, String author, String scope, String subject, float rating) {
        this.name = name;
        this.author = author;
        this.scope = scope;
        this.subject = subject;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
