package com.EnterpriseJavaDevelopment62.BookService.sampleDataPackage;

import com.EnterpriseJavaDevelopment62.BookService.controller.BookRepository;
import com.EnterpriseJavaDevelopment62.BookService.dao.Book;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final Faker faker;

    public SampleDataLoader(BookRepository bookRepository, Faker faker) {
        this.bookRepository = bookRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) {

        List<Book> sampleOrders = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Book(
                        faker.code().isbn10(),
                        faker.book().title(),
                        faker.book().author(),
                        faker.book().genre()
                )).collect(Collectors.toList());

        bookRepository.saveAll(sampleOrders);
    }
}