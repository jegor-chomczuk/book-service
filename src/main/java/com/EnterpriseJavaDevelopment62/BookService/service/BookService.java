package com.EnterpriseJavaDevelopment62.BookService.service;

import com.EnterpriseJavaDevelopment62.BookService.controller.BookRepository;
import com.EnterpriseJavaDevelopment62.BookService.dto.SendingBookDTO;
import com.EnterpriseJavaDevelopment62.BookService.interfaces.IBookService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public String getBookFormat(SendingBookDTO sendingBookDTO) {
        return client.post()
                .uri("/book-format")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

