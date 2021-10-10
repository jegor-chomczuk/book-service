package com.EnterpriseJavaDevelopment62.BookService.interfaces;

import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import com.EnterpriseJavaDevelopment62.BookService.dto.BookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface IBookService {
    String getBookFormat(SendingBookDTO book);
    Book addBook(BookDTO bookDTO);
    BookDTO getCompleteBookDetails(SendingBookDTO sendingBookDTO);
}
