package com.example.demo.repos;

import com.example.demo.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Book findBookByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b WHERE b.publicationYear >= :Year")
    Page<Book> findBooksByYear(@Param("Year") int Year, Pageable pageable);
}
