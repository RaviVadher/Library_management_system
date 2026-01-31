package com.example.demo.service;

import com.example.demo.entities.Author;
import com.example.demo.entities.BorrowRecord;
import com.example.demo.repos.AuthorRepository;
import com.example.demo.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
     public AuthorService(AuthorRepository authorRepository) {
         this.authorRepository = authorRepository;
     }

     public List<Author> getll() {
         return authorRepository.findAll();
     }

     public Author addAuthor(Author author) {
         return authorRepository.save(author);
     }


}
