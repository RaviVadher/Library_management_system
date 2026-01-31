package com.example.demo.dto;
import com.example.demo.entities.Author;
import lombok.Data;
import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Long libraryId;
    private List<Long> authorIds;


}

