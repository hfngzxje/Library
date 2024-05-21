package com.example.Library.dtos.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Long bookId;
    String title;
    String author;
    Integer publicationYear;
    Integer quantity;
}
