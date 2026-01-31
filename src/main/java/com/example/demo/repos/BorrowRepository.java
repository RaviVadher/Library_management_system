package com.example.demo.repos;

import com.example.demo.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

    @Query("SELECT br FROM BorrowRecord br WHERE br.returnDate IS NULL")
    List<BorrowRecord> findAllActiveBorrows();

    @Query("SELECT br FROM BorrowRecord br WHERE br.book.id = :bookId AND br.member.id = :memberId AND br.returnDate IS NULL ")
    List<BorrowRecord> findActiveBorrowByBookAndMember(long bookId, Long memberId);
}
