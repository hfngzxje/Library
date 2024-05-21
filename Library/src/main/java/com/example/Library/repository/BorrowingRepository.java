package com.example.Library.repository;

import com.example.Library.Entities.Borrowings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowings, Long> {
    Optional<Borrowings> findByBookBookIdAndCustomerCustomerId(Long bookId, Long customerId);
}

