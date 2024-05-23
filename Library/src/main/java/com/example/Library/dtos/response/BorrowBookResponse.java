package com.example.Library.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowBookResponse {
    Long borrowingId;
    String bookName;
    String customerName;
    Date borrowingDate;
}
