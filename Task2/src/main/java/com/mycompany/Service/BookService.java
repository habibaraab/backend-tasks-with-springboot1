package com.mycompany.Service;

import com.mycompany.Repositary.BookRepo;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepo.findById(id);
    }

    public Book addBook(Book book) {
       return bookRepo.save(book);
    }

    public boolean deleteBook(int id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.delete(book.get());
        }
       return true;
    }

}
