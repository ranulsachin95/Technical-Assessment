package com.technical.assignment.controller;

import com.technical.assignment.entity.Book;
import com.technical.assignment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return bookService.getBookById(id);

    }
    @PostMapping
    public Book createBook( @RequestBody Book book){
        return bookService.createBook(book);

    }
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book,@PathVariable long id){
        return bookService.updateBook(book,id);

    }
    @GetMapping
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0")  int page,
                                  @RequestParam(defaultValue = "5")  int size
    ){
        return bookService.getAllBooks(page,size);

    }

}
