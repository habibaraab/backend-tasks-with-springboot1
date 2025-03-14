package com.mycompany.Controller;

import com.mycompany.Service.BookService;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class homeController {

    @Autowired
    private BookService bookService;



    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);

    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable int id) {
        Optional<Book> b=bookService.getBookById(id);
        if(b.isPresent()) {
            book.setId(id);
        }
            return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }








}
