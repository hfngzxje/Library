package com.example.Library.dtos.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    String title;
    String author;
    Integer publicationYear;
    Integer quantity;
}
