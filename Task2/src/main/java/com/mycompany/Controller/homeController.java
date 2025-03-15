package com.mycompany.Controller;

import com.mycompany.Service.AuthorService;
import com.mycompany.Service.BookService;
import com.mycompany.model.Author;
import com.mycompany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
            return new ResponseEntity<>(authorService.CreateAuthor(author), HttpStatus.CREATED);
        }

        @GetMapping
        public List<Author> getAllAuthors() {
            return authorService.GetAllAuthors();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
            return authorService.GetAuthorById(id)
                    .map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }



    @PutMapping("/{id}")
    public Author updateBook(@RequestBody Author author, @PathVariable int id) {
        Optional<Author> b=authorService.GetAuthorById(id);
        if(b.isPresent()) {
            author.setId(id);
        }
        return authorService.UpdateAuthor(author);
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
            authorService.DeleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


}
