package com.technical.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name Cannot Be Empty ")
    @NotNull(message = "Name Cannot Be Null")
    private String name;
    private String isbn;
    @ManyToOne
    @JoinColumn(name ="author_id" )
    private Author author;

    public Book(String sampleBook, String number, Author author) {
        this.name=sampleBook;
        this.isbn=number;
        this.author=author;
    }
}
