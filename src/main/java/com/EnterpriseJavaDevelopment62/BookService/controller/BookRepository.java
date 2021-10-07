package com.EnterpriseJavaDevelopment62.BookService.controller;

import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
}
