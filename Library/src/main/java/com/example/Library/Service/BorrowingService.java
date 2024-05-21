package com.example.Library.Service;

import com.example.Library.Entities.Books;
import com.example.Library.Entities.Borrowings;
import com.example.Library.Entities.Customer;
import com.example.Library.Service.ServiceIService.IBorrowingService;
import com.example.Library.dtos.Request.BorrowBookRequest;
import com.example.Library.dtos.Request.ReturnBookRequest;
import com.example.Library.dtos.response.BorrowBookResponse;
import com.example.Library.dtos.response.ReturnBookResponse;
import com.example.Library.enums.ErrorCode;
import com.example.Library.exception.AppException;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowingService implements IBorrowingService {
    BorrowingRepository borrowingRepository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;
    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest) {
        Books book = bookRepository.findById(borrowBookRequest.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_DONOT_EXISTED));
        Customer customer = customerRepository.findById(borrowBookRequest.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_DONOT_EXISTED));

        Optional<Borrowings> existingBorrowing = borrowingRepository.findByBookBookIdAndCustomerCustomerId(borrowBookRequest.getBookId(), borrowBookRequest.getCustomerId());
        if (existingBorrowing.isPresent()) {
            throw new AppException(ErrorCode.ALREADY);
        }
        if(book.getQuantity()<=0){
            throw new AppException(ErrorCode.OUT_OF_STOCK);
        }
        book.setQuantity(book.getQuantity()-1);
        Borrowings borrowing = new Borrowings();
        borrowing.setBook(book);
        borrowing.setCustomer(customer);
        borrowing.setBorrowingDate(borrowBookRequest.getBorrowingDate());
        bookRepository.save(book);
        Borrowings savedBorrowing = borrowingRepository.save(borrowing);

        return new BorrowBookResponse(
                savedBorrowing.getBorrowingId(),
                savedBorrowing.getBook().getTitle(),
                savedBorrowing.getCustomer().getCustomerName(),
                savedBorrowing.getBorrowingDate()
        );
    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest returnBookRequest) {
        Books book = bookRepository.findById(returnBookRequest.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_DONOT_EXISTED));
        Customer customer = customerRepository.findById(returnBookRequest.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_DONOT_EXISTED));

        Borrowings borrowing = borrowingRepository.findByBookBookIdAndCustomerCustomerId(returnBookRequest.getBookId(), returnBookRequest.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_BORROWED));

        borrowingRepository.delete(borrowing);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        return new ReturnBookResponse(
                borrowing.getBorrowingId(),
                book.getTitle(),
                customer.getCustomerName()
        );
    }

}
