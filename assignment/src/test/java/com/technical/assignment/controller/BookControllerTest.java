package com.technical.assignment.controller;

import com.technical.assignment.entity.Author;
import com.technical.assignment.entity.Book;
import com.technical.assignment.exception.ResourceNotFoundException;
import com.technical.assignment.repository.BookRepository;
import com.technical.assignment.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookControllerTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @InjectMocks
    private BookService bookService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("book 1", "123456789", new Author(2,"sachin ", "ranul")));
        Page<Book> bookPage = new PageImpl<>(books);
        when(bookRepository.findAll(any(PageRequest.class))).thenReturn(bookPage);

        List<Book> result = bookService.getAllBooks(0, 10);
        assertEquals(1, result.size());
        assertEquals("book 1", result.get(0).getName());
    }

    @Test
    void testGetBookById() {
        Book book = new Book("book 2", "1323456789", new Author(3,"sachin 1 ", "ranul1"));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1);
        assertEquals("book 2", result.getName());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(1);
        });
    }

    @Test
    void testCreateBook() {
        Book book = new Book("book 3", "1323456789", new Author(4,"sachin 2 ", "ranul2"));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.createBook(book);
        assertEquals("book 3", result.getName());
    }

    @Test
    void testUpdateBook() {
        Book existingBook = new Book("book 4", "323233222", new Author(4,"sachin 3", "ranul3"));
        Book updatedDetails =  new Book("book 5", "32322", new Author(5,"sachin 4", "ranul4"));

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedDetails);

        Book result = bookService.updateBook( updatedDetails,1L);
        assertEquals("book 5", result.getName());
    }

    @Test
    void testUpdateBookNotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        Book updatedDetails =  new Book("book 6", "32343322", new Author(6,"sachin 6", "ranul6"));

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook( updatedDetails,1);
        });
    }
}
