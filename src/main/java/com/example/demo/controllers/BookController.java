package com.example.demo.controllers;
import com.example.demo.entities.Book;
import com.example.demo.service.BookService;
import com.example.demo.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // Field Injection
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody BookDTO bookDTO) {
         return bookService.saveBook(bookDTO);
    }
    @GetMapping("/{Id}")
    public Book getUserById(@PathVariable Long Id) {
        return bookService.GetById(Id);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<Book> books = bookService.findAllBooksPaginatedAndSorted(page, size, sortBy, sortDir);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/year/{Year}")
    public ResponseEntity<Page<Book>> getAllBooksByYear(
            @PathVariable int Year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        int currYear = java.time.LocalDate.now().getYear();
        if(Year< 1500 || Year > currYear ) {
            throw new IllegalArgumentException("Invalid Year");
        }
        Page<Book> books = bookService.findAllBooksByYearPaginatedAndSorted(Year,page, size, sortBy, sortDir);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/search")
    public ResponseEntity<Book> searchBookByIsbn(@RequestParam String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }



}

