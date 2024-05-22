package com.example.Library.Service.ServiceIService;

import com.example.Library.dtos.Request.BorrowBookRequest;
import com.example.Library.dtos.Request.ReturnBookRequest;
import com.example.Library.dtos.response.BorrowBookResponse;
import com.example.Library.dtos.response.CustomerResponse;
import com.example.Library.dtos.response.ReturnBookResponse;

import java.util.List;

public interface IBorrowingService {
    BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest);

    ReturnBookResponse returnBook(ReturnBookRequest returnBookRequest);

    List<BorrowBookResponse> getAllBorrow(int page, int pageSize);
}
