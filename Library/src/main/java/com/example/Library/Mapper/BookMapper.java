package com.example.Library.Mapper;

import com.example.Library.Entities.Books;
import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toBookResponse(Books book);
    Books toBooks(BookRequest bookRequest);

    void UpdateBook(@MappingTarget Books books, BookRequest request);
}
