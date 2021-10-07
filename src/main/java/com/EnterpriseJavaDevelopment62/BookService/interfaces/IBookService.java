package com.EnterpriseJavaDevelopment62.BookService.interfaces;

import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;

public interface IBookService {
    String getBookFormat(SendingBookDTO book);
}
