package com.EnterpriseJavaDevelopment62.BookService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendingBookDTO {

    @NonNull
    private String isbn;
}
