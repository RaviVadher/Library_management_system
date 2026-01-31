package com.example.demo.dto;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Library;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public static Book toDTO(BookDTO dto, Library library, List<Author> authors) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());

            book.setLibrary(library);
            book.setAuthors(authors);

        return book;
    }
}
