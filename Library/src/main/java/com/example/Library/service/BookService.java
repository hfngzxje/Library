package com.example.Library.service;

import com.example.Library.client.BookServiceClient;
import com.example.Library.entities.Books;
import com.example.Library.mapper.BookMapper;
import com.example.Library.repository.specifications.BookSpec;
import com.example.Library.service.serviceIService.IBookService;
import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.response.BookResponse;
import com.example.Library.enums.ErrorCode;
import com.example.Library.exception.AppException;
import com.example.Library.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService implements IBookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        if(bookRequest.getPublicationYear()<0 || bookRequest.getPublicationYear()>9999){
            throw new AppException(ErrorCode.INVALID_YEAR);
        }else if(bookRequest.getQuantity()<0){
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }
        Books book = bookMapper.toBooks(bookRequest);
        bookRepository.save(book);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest bookRequest) {
       Books books = bookRepository.findById(bookId).orElseThrow(()
               -> new AppException(ErrorCode.BOOK_DONOT_EXISTED));
        if(bookRequest.getPublicationYear()<0 || bookRequest.getPublicationYear()>9999){
            throw new AppException(ErrorCode.INVALID_YEAR);
        }else if(bookRequest.getQuantity()<0){
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }
       bookMapper.updateBook(books,bookRequest);
       return bookMapper.toBookResponse(bookRepository.save(books));
    }

    @Override
    public void deleteBook(Long bookId) {
        if(!bookRepository.existsById(bookId)){
            throw new AppException(ErrorCode.BOOK_DONOT_EXISTED);
        }
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_DONOT_EXISTED));
        return bookMapper.toBookResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Books> bookPage = bookRepository.findAll(pageable);

        return bookPage.getContent().stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> searchByTitle(int page, int pageSize, String title) {
        Specification<Books> spec = BookSpec.searchByTitle(title);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Books> booksPage = bookRepository.findAll(spec,pageable);
        return booksPage.getContent().stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    private final BookServiceClient bookServiceClient;

    public BookResponse getBookByIdd(Long bookId) {
        return bookServiceClient.findByBookId(bookId).getResult();
    }

    public List<BookResponse> getAllBookss(int page, int pageSize) {
        return bookServiceClient.getAllBooks(page, pageSize).getResult();
    }

    public List<BookResponse> searchByTitlee(int page, int pageSize, String title) {
        return bookServiceClient.searchByTitle(page, pageSize, title).getResult();
    }

}
