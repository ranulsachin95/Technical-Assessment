package com.technical.assignment.dto;

import com.technical.assignment.entity.Author;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private String isbn;
    private Author author;
}
