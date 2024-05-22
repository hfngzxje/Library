package com.example.Library.Mapper;

import com.example.Library.Entities.Books;
import com.example.Library.Entities.Borrowings;
import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.BookResponse;
import com.example.Library.dtos.response.BorrowBookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowMapper {
    @Mapping(source = "book.title",target = "bookName")
    @Mapping(source = "customer.customerName",target = "customerName")
    BorrowBookResponse toBorrowResponse(Borrowings borrowings);
}
