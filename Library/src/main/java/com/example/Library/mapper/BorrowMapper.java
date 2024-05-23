package com.example.Library.mapper;

import com.example.Library.entities.Borrowings;
import com.example.Library.dtos.response.BorrowBookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowMapper {
    @Mapping(source = "book.title",target = "bookName")
    @Mapping(source = "customer.customerName",target = "customerName")
    BorrowBookResponse toBorrowResponse(Borrowings borrowings);
}
