package com.mycompany.Controller;

import com.mycompany.Service.AuthorService;
import com.mycompany.Service.BookService;
import com.mycompany.model.Author;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class homeController {

        @Autowired
        private AuthorService authorService;
        @Autowired
        private BookService bookService;

        @PostMapping
        public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
                return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Author>> getAllAuthors() {
                return ResponseEntity.ok(authorService.getAllAuthors());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
                return ResponseEntity.ok(authorService.getAuthorById(id)
                        .orElseThrow(() -> new RuntimeException("Author not found")));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @Valid @RequestBody Author author) {
                return ResponseEntity.ok(authorService.updateAuthor(id, author));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
                authorService.deleteAuthor(id);
                return ResponseEntity.noContent().build();
        }


        @PostMapping("/{authorId}/books")
        public ResponseEntity<Book> createBook(@PathVariable Long authorId, @Valid @RequestBody Book book) {
                return new ResponseEntity<>(bookService.createBook(authorId, book), HttpStatus.CREATED);
        }

        @GetMapping("/{authorId}/books")
        public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {
                return ResponseEntity.ok(bookService.getBooksByAuthorId(authorId));
        }

        @GetMapping("/{authorId}/books/{bookId}")
        public ResponseEntity<Book> getBookById(@PathVariable Long authorId, @PathVariable Long bookId) {
                return ResponseEntity.ok(bookService.getBookById(authorId, bookId)
                        .orElseThrow(() -> new RuntimeException("Book not found")));
        }

        @PutMapping("/{authorId}/books/{bookId}")
        public ResponseEntity<Book> updateBook(@PathVariable Long authorId, @PathVariable Long bookId,
                                               @Valid @RequestBody Book book) {
                return ResponseEntity.ok(bookService.updateBook(authorId, bookId, book));
        }

        @DeleteMapping("/{authorId}/books/{bookId}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long authorId, @PathVariable Long bookId) {
                bookService.deleteBook(authorId, bookId);
                return ResponseEntity.noContent().build();
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }


}
