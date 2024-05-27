package com.example.Library.mapper;

import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.BookResponse;
import com.example.Library.entities.Books;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-27T09:17:17+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponse toBookResponse(Books book) {
        if ( book == null ) {
            return null;
        }

        BookResponse.BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.bookId( book.getBookId() );
        bookResponse.title( book.getTitle() );
        bookResponse.author( book.getAuthor() );
        bookResponse.publicationYear( book.getPublicationYear() );
        bookResponse.quantity( book.getQuantity() );

        return bookResponse.build();
    }

    @Override
    public Books toBooks(BookRequest bookRequest) {
        if ( bookRequest == null ) {
            return null;
        }

        Books.BooksBuilder books = Books.builder();

        books.title( bookRequest.getTitle() );
        books.author( bookRequest.getAuthor() );
        books.publicationYear( bookRequest.getPublicationYear() );
        books.quantity( bookRequest.getQuantity() );

        return books.build();
    }

    @Override
    public void updateBook(Books books, BookRequest request) {
        if ( request == null ) {
            return;
        }

        books.setTitle( request.getTitle() );
        books.setAuthor( request.getAuthor() );
        books.setPublicationYear( request.getPublicationYear() );
        books.setQuantity( request.getQuantity() );
    }
}
