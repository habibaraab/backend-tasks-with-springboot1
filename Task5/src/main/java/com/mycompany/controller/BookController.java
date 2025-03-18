package com.mycompany.controller;

import com.mycompany.Service.BookService;
import com.mycompany.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book>createBook(@RequestBody @Valid Book book) {
        return new ResponseEntity<>(bookService.CreateBook(book), HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.GetAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.GetBookById(id).orElseThrow(()->new RuntimeException("Book not found")), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Valid Book book) {
        return new ResponseEntity<>(bookService.UpdateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.DeleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
