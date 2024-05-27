package com.example.Library.client;

import com.example.Library.dtos.response.ApiResponse;
import com.example.Library.dtos.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8080/api/book")
public interface BookServiceClient {

    @GetMapping("/{bookId}")
    ApiResponse<BookResponse> findByBookId(@PathVariable("bookId") Long bookId);

    @GetMapping
    ApiResponse<List<BookResponse>> getAllBooks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize);

    @GetMapping("/searchByTitle")
    ApiResponse<List<BookResponse>> searchByTitle(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("title") String title);
}
