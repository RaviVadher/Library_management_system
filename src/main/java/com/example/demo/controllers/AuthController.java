package com.example.demo.controllers;

import com.example.demo.entities.Author;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthController {

    private final AuthorService authorService;
    public AuthController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public List<Author> getAuthors() {
        return authorService.getll();
    }

    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author) {

        return authorService.addAuthor(author);
    }
}
