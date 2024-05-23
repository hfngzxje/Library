package com.example.Library.service;

import com.example.Library.dtos.Request.BorrowBookRequest;
import com.example.Library.dtos.Request.ReturnBookRequest;
import com.example.Library.dtos.response.BorrowBookResponse;
import com.example.Library.dtos.response.ReturnBookResponse;
import com.example.Library.entities.Books;
import com.example.Library.entities.Borrowings;
import com.example.Library.entities.Customer;
import com.example.Library.enums.ErrorCode;
import com.example.Library.exception.AppException;
import com.example.Library.mapper.BorrowMapper;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.repository.CustomerRepository;
import com.example.Library.repository.specifications.BorrowSpec;
import com.example.Library.service.serviceIService.IBorrowingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowingService implements IBorrowingService {
    BorrowingRepository borrowingRepository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;
    BorrowMapper borrowMapper;
    @Override
    @Transactional
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
    @Transactional
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

    @Override
    public List<BorrowBookResponse> getAllBorrow(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Borrowings> bookPage = borrowingRepository.findAll(pageable);

        return bookPage.getContent().stream()
                .map(borrowMapper::toBorrowResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowBookResponse> searchByBookNameAndCustomerName(int page, int pageSize, String input) {
        Specification<Borrowings> spec = BorrowSpec.searchByBookTitle(input);
        Specification<Borrowings> spec2 = BorrowSpec.searchByCustomerName(input);
        Specification<Borrowings> finalSpec = spec.or(spec2);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Borrowings> borrowingsPage = borrowingRepository.findAll(finalSpec,pageable);
        return borrowingsPage.getContent().stream()
                .map(borrowMapper::toBorrowResponse)
                .collect(Collectors.toList());
    }

}
