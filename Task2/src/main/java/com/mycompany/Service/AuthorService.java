package com.mycompany.Service;

import com.mycompany.Repositary.AuthorRepository;
import com.mycompany.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author CreateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> GetAuthorById(int id) {
        return authorRepository.findById(id);
    }
    public List<Author> GetAllAuthors() {
        return authorRepository.findAll();

    }
    public Author UpdateAuthor(Author author) {
        return authorRepository.save(author);
    }
    public void DeleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

}
