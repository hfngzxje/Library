package com.example.Library.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReturnBookResponse {
    Long borrowingId;
    String bookTitle;
    String customerName;
}
