package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.libraryDto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {

    BooksService booksService;
    BooksRepository booksRepository;


    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/welcome")
    public String getGreetings() {
        return "Welcome Biblioteca";
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getMovies() {
        List<BookDto> allBookDto = booksService.getAllBooks();
        return ResponseEntity.ok().body(allBookDto);
    }


}
