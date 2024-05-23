package com.example.Library.dtos.Request;

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
