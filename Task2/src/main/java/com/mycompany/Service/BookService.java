package com.mycompany.Service;

import com.mycompany.Repositary.AuthorRepository;
import com.mycompany.Repositary.BookRepository;
import com.mycompany.model.Author;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Book createBook(Long authorId, Book book) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Optional<Book> getBookById(Long authorId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.getAuthor().getId().equals(authorId)) {
            throw new RuntimeException("Book does not belong to this author");
        }
        return Optional.of(book);
    }

    public Book updateBook(Long authorId, Long bookId, Book bookDetails) {
        Book book = getBookById(authorId, bookId).get();
        book.setTitle(bookDetails.getTitle());
        book.setPublishedDate(bookDetails.getPublishedDate());
        return bookRepository.save(book);
    }

    public void deleteBook(Long authorId, Long bookId) {
        Book book = getBookById(authorId, bookId).get();
        bookRepository.delete(book);
    }



}
