package com.example.Library.repository;

import com.example.Library.Entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    Books findByTitle(String title);
}
