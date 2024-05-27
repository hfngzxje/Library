package com.example.Library.controller;

import com.example.Library.service.BookService;
import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.ApiResponse;
import com.example.Library.dtos.response.BookResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BookController {
    BookService bookService;

    @PostMapping()
    ApiResponse<BookResponse> createBook(@RequestBody BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .massage("Add book successfully!!")
                .result(bookService.addBook(request))
                .build();
    }

    @GetMapping("{bookId}")
    ApiResponse<BookResponse> findByBookId(@PathVariable Long bookId) {
        BookResponse bookResponse = bookService.getBookById(bookId);
        return ApiResponse.<BookResponse>builder()
                .result(bookResponse)
                .build();
    }

    @GetMapping()
    ApiResponse<List<BookResponse>> getAllBook(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int pageSize) {
        List<BookResponse> list = bookService.getAllBooks(page,pageSize);
        return ApiResponse.<List<BookResponse>>builder()
                .result(list)
                .build();
    }

    @DeleteMapping("{bookId}")
    ApiResponse<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ApiResponse.<Void>builder()
                .massage("Delete successfully!!")
                .build();
    }

    @PutMapping("{bookId}")
    ApiResponse<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        return ApiResponse.<BookResponse>builder()
                .massage("Update successfully!!")
                .result(bookService.updateBook(bookId,bookRequest))
                .build();
    }

    @GetMapping("searchByTitle")
    ApiResponse<List<BookResponse>> searchByTitle(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int pageSize,
                                                  @RequestParam(required = false) String title) {
        List<BookResponse> list = bookService.searchByTitle(page,pageSize,title);
        return ApiResponse.<List<BookResponse>>builder()
                .result(list)
                .massage("Search successfully!!")
                .build();
    }


}
