package com.example.Library.Service.ServiceIService;

import com.example.Library.dtos.Request.BorrowBookRequest;
import com.example.Library.dtos.Request.ReturnBookRequest;
import com.example.Library.dtos.response.BorrowBookResponse;
import com.example.Library.dtos.response.ReturnBookResponse;

public interface IBorrowingService {
    BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest);

    ReturnBookResponse returnBook(ReturnBookRequest returnBookRequest);
}
