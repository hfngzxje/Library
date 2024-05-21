package com.example.Library.Service.ServiceIService;

import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.BookResponse;

import java.util.List;

public interface IBookService {
    BookResponse addBook(BookRequest bookRequest);
    BookResponse updateBook(Long bookId, BookRequest bookRequest);
    void deleteBook(Long bookId);
    BookResponse getBookById(Long bookId);
    List<BookResponse> getAllBooks(int page, int pageSize);


}
