package com.mycompany.model;



import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Author is required")
    private String author;



    @Column(nullable = false)
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    public Book(){}
    public Book(int id, String title, String author, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is required") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is required") String author) {
        this.author = author;
    }

    public @Positive(message = "Price must be positive") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Price must be positive") BigDecimal price) {
        this.price = price;
    }
}
