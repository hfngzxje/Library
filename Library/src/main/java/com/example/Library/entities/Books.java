package com.example.Library.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    Long bookId;

    @Column(name = "title", nullable = false, length = 200)
    String title;

    @Column(name = "author", length = 100)
    String author;

    @Column(name = "publication_year")
    Integer publicationYear;

    @Column(name = "quantity")
    Integer quantity;

}
