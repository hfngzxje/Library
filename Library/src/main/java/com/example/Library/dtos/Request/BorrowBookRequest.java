package com.example.Library.dtos.Request;

import com.example.Library.Entities.Books;
import com.example.Library.Entities.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowBookRequest {
    Long bookId;
    Long customerId;
    Date borrowingDate;
}
