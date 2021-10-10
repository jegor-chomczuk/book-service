package com.EnterpriseJavaDevelopment62.BookService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivingBookDTO {
    @NonNull
    private List<String> formats;
}
