package com.EnterpriseJavaDevelopment62.BookService.service;

import com.EnterpriseJavaDevelopment62.BookService.controller.BookRepository;
import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import com.EnterpriseJavaDevelopment62.BookService.dto.BookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.ReceivingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.interfaces.IBookService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService implements IBookService {

    public static final String TARGET_SERVICE = "book-format-service";
    final BookRepository bookRepository;
    final DiscoveryClient discoveryClient;
    private WebClient client;

    public BookService(BookRepository bookRepository, DiscoveryClient discoveryClient) {
        this.bookRepository = bookRepository;
        this.discoveryClient = discoveryClient;
        createClient();
    }

    private void createClient() {
        var serviceInstanceList = discoveryClient.getInstances(TARGET_SERVICE);
        String clientURI = serviceInstanceList.get(0).getUri().toString();
        client = WebClient.create(clientURI);
    }

    //  Retrieve a String of formats from BOOK-FORMAT-SERVICE
    public String getBookFormat(SendingBookDTO sendingBookDTO) {
        return client.post()
                .uri("/book-format")
                .body(Mono.just(sendingBookDTO), SendingBookDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Add a new book
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre());
        book = bookRepository.save(book);
        return new Book(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getGenre());
    }

    //  Retrieve book formats from BOOK-FORMAT-SERVICE and return bookDTO with complete details
    public BookDTO getCompleteBookDetails(SendingBookDTO sendingBookDTO) {
        String formats = getBookFormat(sendingBookDTO);
        ReceivingBookDTO receivingBookDTO = new ReceivingBookDTO(getFormatList(formats));
        Book book = bookRepository.getById(sendingBookDTO.getIsbn());
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getGenre(), receivingBookDTO.getFormats());
    }

    //  Parse format String into a list of Formats
    public List<String> getFormatList(String formats) {
        String prefix = "\\{\\\"formats\\\":\\[\\\"";
        String suffix = "\\\"]\\}";
        String joiner = "\\\",\\\"";
        String parsedFormats = formats.replaceAll(prefix, "").replaceAll(suffix, "").replaceAll(joiner, ", ");

        List<String> formatList = Arrays.asList(parsedFormats.split("\\s*,\\s*"));

        return formatList;
    }
}

