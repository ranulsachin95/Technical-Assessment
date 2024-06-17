package com.technical.assignment.controller;

import com.technical.assignment.entity.Author;
import com.technical.assignment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable long id){
        return authorService.getAuthorById(id);

    }
    @PostMapping
    public Author createAuthor( @RequestBody Author author){
        return authorService.createAuthor(author);

    }
    @PutMapping
    public Author updateAuthor(@RequestBody Author authorDetails,@PathVariable long id){
       return authorService.updateAuthor(authorDetails,id);

    }
}
