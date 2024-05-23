package com.example.Library.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.Date;
@Data
@Entity
@Table(name = "borrowings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Borrowings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id")
    Long borrowingId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Books book;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @Column(name = "borrowing_date")
    Date borrowingDate;
}
