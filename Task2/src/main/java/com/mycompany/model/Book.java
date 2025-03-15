package com.mycompany.model;


import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String publishedDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public Book(Long id, String title, String publishedDate, Author author) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.author = author;
    }






    public Book(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + '\'' + '}';
    }
}
