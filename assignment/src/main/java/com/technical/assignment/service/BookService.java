package com.technical.assignment.service;

import com.technical.assignment.entity.Book;
import com.technical.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Page<Book> getAllBooks(int page, int size){
        return bookRepository.findAll(PageRequest.of(page,size));
    }
    public Optional<Book> getBookById(long id){
        return bookRepository.findById(id);

    }
    public Book createBook(Book book){
        return bookRepository.save(book);

    }
    public Book updateBook(Book bookDetails,long id){
        Book book=bookRepository.findById(id).orElseThrow();
        book.setIsbn(bookDetails.getIsbn());
        book.setName(bookDetails.getName());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);

    }
}
