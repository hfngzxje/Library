package com.example.Library.dtos.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String customerName;
    Integer age;
}
