package com.example.Library.repository;

import com.example.Library.entities.Borrowings;
import com.example.Library.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowings, Long>, JpaSpecificationExecutor<Borrowings> {
    Optional<Borrowings> findByBookBookIdAndCustomerCustomerId(Long bookId, Long customerId);
}

