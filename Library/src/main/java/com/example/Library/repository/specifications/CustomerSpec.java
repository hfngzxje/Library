package com.example.Library.repository.specifications;

import com.example.Library.entities.Books;
import com.example.Library.entities.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpec {
    public static Specification<Customer> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("customerName")), "%" + name.toLowerCase() + "%");
        }
    }
}
