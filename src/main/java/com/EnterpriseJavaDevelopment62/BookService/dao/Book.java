package com.EnterpriseJavaDevelopment62.BookService.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_table")
public class Book {
    @Id
    private String isbn;

    private String title;

    private String author;

    private String genre;
}
