package com.example.Library.repository;

import com.example.Library.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books, Long>, JpaSpecificationExecutor<Books> {
    Books findByTitle(String title);
}
