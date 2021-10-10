package com.EnterpriseJavaDevelopment62.BookService.controller;

import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import com.EnterpriseJavaDevelopment62.BookService.dto.BookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableEurekaClient
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IBookService bookService;

    //  Get book by ISBN
    @GetMapping("/book/{isbn}")
    public Book findById(@PathVariable("isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    //   Get all books
    @GetMapping("/books")
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    // Add a new book
    @PostMapping("/book/add")
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    //  Retrieve book formats from BOOK-FORMAT-SERVICE and return bookDTO with complete details
    @PostMapping("/book-format")
    public BookDTO getCompleteBookDetails(@RequestBody SendingBookDTO sendingBookDTO) {
        return bookService.getCompleteBookDetails(sendingBookDTO);
    }
}