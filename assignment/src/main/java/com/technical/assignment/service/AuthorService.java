package com.technical.assignment.service;

import com.technical.assignment.entity.Author;
import com.technical.assignment.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Optional<Author> getAuthorById(long id){
        return authorRepository.findById(id);

    }
    public Author createAuthor(Author author){
        return authorRepository.save(author);

    }
    public Author updateAuthor(Author authorDetails,long id){
        Author author=authorRepository.findById(id).orElseThrow();
        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        return authorRepository.save(author);

    }



}
