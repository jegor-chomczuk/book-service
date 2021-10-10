package com.EnterpriseJavaDevelopment62.BookService.dto;

import com.EnterpriseJavaDevelopment62.BookService.enums.Format;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @NonNull
    private String isbn;

    @NonNull
    private String title;

    @NonNull
    private String author;

    @NonNull
    private String genre;

    @NonNull
    private List<String> formats;
}
