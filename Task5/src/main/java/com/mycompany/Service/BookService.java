package com.mycompany.Service;

import com.mycompany.Repository.BookRepository;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book CreateBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> GetAllBooks() {
        return bookRepository.findAll();
    }
   public Optional<Book> GetBookById(Long id) {
        return bookRepository.findById(id);
   }
   public Book UpdateBook(Long id,Book book) {
        Book oldBook = bookRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublishdate(book.getPublishdate());
        return bookRepository.save(oldBook);
   }

   public void DeleteBook(Long id) {
    Book book = bookRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    bookRepository.delete(book);


   }
}

