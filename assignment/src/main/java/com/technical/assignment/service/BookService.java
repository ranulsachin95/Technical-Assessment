package com.technical.assignment.service;

import com.technical.assignment.entity.Book;
import com.technical.assignment.exception.ResourceNotFoundException;
import com.technical.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Page<Book> getAllBooks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }
    public Book getBookById(long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));


    }
    public Book createBook(Book book){
        return bookRepository.save(book);

    }
    public Book updateBook(Book bookDetails,long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        book.setName(bookDetails.getName());
        book.setIsbn(bookDetails.getIsbn());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);


    }
}
