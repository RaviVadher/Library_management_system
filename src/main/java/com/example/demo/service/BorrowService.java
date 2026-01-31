package com.example.demo.service;

import com.example.demo.entities.Book;
import com.example.demo.entities.BorrowRecord;
import com.example.demo.entities.Member;
import com.example.demo.repos.BookRepository;
import com.example.demo.repos.BorrowRepository;
import com.example.demo.repos.MemberRepository;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BorrowService( BorrowRepository borrowRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public BorrowRecord borrowBook(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not founded"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member is not found"));

        if (!borrowRepository
                .findActiveBorrowByBookAndMember(bookId, memberId)
                .isEmpty()) {
            throw new RuntimeException("Book already borrowed by this member");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setMember(member);
        record.setStatus("borrowed");
        record.setBorrowDate(java.time.LocalDate.now());
        return borrowRepository.save(record);
    }

    public BorrowRecord returnBook(Long borrowRecordId) {

        BorrowRecord record = borrowRepository.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        record.setReturnDate(java.time.LocalDate.now());

        return borrowRepository.save(record);
    }

    public List<BorrowRecord> getAllActiveBorrows() {
        return borrowRepository.findAllActiveBorrows();
    }
}
