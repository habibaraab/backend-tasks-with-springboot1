package com.mycompany.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String birthdate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
    public Author() {

    }

    public Author(int id, String name, String birthdate, List<Book> books) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.books = books;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }


    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + '\'' + '}';
    }
}


