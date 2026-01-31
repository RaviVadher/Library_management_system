package com.example.demo.service;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.BookMapper;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Library;
import com.example.demo.repos.AuthorRepository;
import com.example.demo.repos.BookRepository;
import com.example.demo.repos.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private  final LibraryRepository libraryRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
    }


    public Book saveBook(BookDTO bookDTO) {

        Library  library = libraryRepository.findById(bookDTO.getLibraryId()).orElseThrow(()-> new RuntimeException("Libray not found"));
        List<Author> authors = new ArrayList<>(authorRepository.findAllById(bookDTO.getAuthorIds()));
        Book book = BookMapper.toDTO(bookDTO,library, authors);

        return bookRepository.save(book);
    }

   public List<Book> GetAll(){
        List<Book> allBooks = bookRepository.findAll();
       return allBooks;
   }
    public Book GetById(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        return (book);
    }
    public Page<Book> findAllBooksPaginatedAndSorted(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage;
    }

    public Page<Book> findAllBooksByYearPaginatedAndSorted(int year,int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<Book> bookPage = bookRepository.findBooksByYear(year,pageable);
        return bookPage;
    }


    public Book getBookByIsbn(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        if (book == null) {
            throw new RuntimeException("Book with ISBN " + isbn + " not found.");
        }
        return book;
    }

}
