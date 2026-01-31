package com.example.demo.controllers;


import com.example.demo.entities.BorrowRecord;
import com.example.demo.repos.BorrowRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private  final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/book/{bookId}/member/{memberId}")
    public BorrowRecord borrowBook(
            @PathVariable Long bookId,
            @PathVariable  Long memberId
    ) {
        return borrowService.borrowBook(bookId, memberId);
    }


    @PostMapping("/return/{borrowId}")
    public BorrowRecord returnBook(@PathVariable Long borrowId) {
        return borrowService.returnBook(borrowId);
    }

    @GetMapping("/active")
    public List<BorrowRecord> getAllActiveBorrows() {
        return borrowService.getAllActiveBorrows();
    }
}
