package com.example.Library.Mapper;

import com.example.Library.Entities.Books;
import com.example.Library.Entities.Borrowings;
import com.example.Library.Entities.Customer;
import com.example.Library.dtos.response.BorrowBookResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T14:13:46+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BorrowMapperImpl implements BorrowMapper {

    @Override
    public BorrowBookResponse toBorrowResponse(Borrowings borrowings) {
        if ( borrowings == null ) {
            return null;
        }

        BorrowBookResponse.BorrowBookResponseBuilder borrowBookResponse = BorrowBookResponse.builder();

        borrowBookResponse.bookName( borrowingsBookTitle( borrowings ) );
        borrowBookResponse.customerName( borrowingsCustomerCustomerName( borrowings ) );
        borrowBookResponse.borrowingId( borrowings.getBorrowingId() );
        borrowBookResponse.borrowingDate( borrowings.getBorrowingDate() );

        return borrowBookResponse.build();
    }

    private String borrowingsBookTitle(Borrowings borrowings) {
        if ( borrowings == null ) {
            return null;
        }
        Books book = borrowings.getBook();
        if ( book == null ) {
            return null;
        }
        String title = book.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String borrowingsCustomerCustomerName(Borrowings borrowings) {
        if ( borrowings == null ) {
            return null;
        }
        Customer customer = borrowings.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String customerName = customer.getCustomerName();
        if ( customerName == null ) {
            return null;
        }
        return customerName;
    }
}
