package com.example.Library.repository.specifications;

import com.example.Library.dtos.response.BookResponse;
import com.example.Library.entities.Books;
import org.springframework.data.jpa.domain.Specification;

public class BookSpec {
    public static Specification<Books> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        }
    }
}
