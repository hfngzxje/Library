package com.example.Library.Controller;

import com.example.Library.Service.ServiceIService.IBorrowingService;
import com.example.Library.dtos.Request.BorrowBookRequest;
import com.example.Library.dtos.Request.ReturnBookRequest;
import com.example.Library.dtos.response.ApiResponse;
import com.example.Library.dtos.response.BorrowBookResponse;
import com.example.Library.dtos.response.CustomerResponse;
import com.example.Library.dtos.response.ReturnBookResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowingController {

    IBorrowingService borrowingService;

    @PostMapping("/borrow")
    public ApiResponse<BorrowBookResponse> borrowBook(@RequestBody BorrowBookRequest borrowBookRequest) {
        BorrowBookResponse response = borrowingService.borrowBook(borrowBookRequest);
        return ApiResponse.<BorrowBookResponse>builder()
                .massage("Borrow successfully!!")
                .result(response)
                .build();
    }

    @PostMapping("/return")
    public ApiResponse<ReturnBookResponse> returnBook(@RequestBody ReturnBookRequest returnBookRequest) {
        ReturnBookResponse response = borrowingService.returnBook(returnBookRequest);
        return ApiResponse.<ReturnBookResponse>builder()
                .massage("Return successfully!!")
                .result(response)
                .build();
    }
}
