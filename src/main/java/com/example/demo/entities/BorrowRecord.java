package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate borrowDate;
    @Column
    private LocalDate returnDate;

    @Column(nullable = false)
    private String status;

    // Many-to-One relationship with Member
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Many-to-One relationship with Book
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}

