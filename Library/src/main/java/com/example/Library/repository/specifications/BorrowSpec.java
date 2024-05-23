package com.example.Library.repository.specifications;

import com.example.Library.entities.Books;
import com.example.Library.entities.Borrowings;
import com.example.Library.entities.Customer;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BorrowSpec {
    public static Specification<Borrowings> searchByBookTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        } else {
            return (root, query, criteriaBuilder) -> {
                Join<Borrowings, Books> bookJoin = root.join("book");
                return criteriaBuilder.like(criteriaBuilder.lower(bookJoin.get("title")), "%" + title.toLowerCase() + "%");
            };
        }
    }

    public static Specification<Borrowings> searchByCustomerName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        } else {
            return (root, query, criteriaBuilder) -> {
                Join<Borrowings, Customer> customerJoin = root.join("customer");
                return criteriaBuilder.like(criteriaBuilder.lower(customerJoin.get("customerName")), "%" + name.toLowerCase() + "%");
            };
        }
    }
}
