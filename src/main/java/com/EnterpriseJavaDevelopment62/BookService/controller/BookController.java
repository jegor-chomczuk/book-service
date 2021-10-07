package com.EnterpriseJavaDevelopment62.BookService.controller;

import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import com.EnterpriseJavaDevelopment62.BookService.dto.BookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.ReceivingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.interfaces.IBookService;
import com.EnterpriseJavaDevelopment62.BookService.service.BookService;
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

    @GetMapping("/book/{isbn}")
    public Book findById(@PathVariable("isbn") String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    @GetMapping("/books")
    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    @PostMapping("/book/add")
    public Book addBook(@RequestBody BookDTO bookDTO){
        Book book = new Book(bookDTO.getIsbn(),bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre());
        book = bookRepository.save(book);
        return new Book(book.getIsbn(),book.getTitle(), book.getAuthor(), book.getGenre());
    }

//    @GetMapping("/book-format")
//    public BookDTO getCompleteBookDetails(@RequestBody SendingBookDTO sendingBookDTO){
//        ReceivingBookDTO receivingBookDTO = new ReceivingBookDTO();
//        receivingBookDTO.setFormats(bookService.getBookFormat(sendingBookDTO));
//        var book = bookRepository.getById(sendingBookDTO.getIsbn());
//        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getGenre(), receivingBookDTO.getFormats());
//    }

    @PostMapping("/book-format")
    public String getBookFormat(@RequestBody SendingBookDTO sendingBookDTO){
        return bookService.getBookFormat(sendingBookDTO);
    }
}