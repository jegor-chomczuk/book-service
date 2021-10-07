package com.EnterpriseJavaDevelopment62.BookService.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResultDTO {

        @NonNull
        private String isbn;

        @NonNull
        private String title;

        @NonNull
        private String author;

        @NonNull
        private String genre;
    }
