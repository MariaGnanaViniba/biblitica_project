package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.service.BooksService;
import com.vapasi.biblioteca.service.CustomerBookMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {

    BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {

        this.booksService = booksService;
    }

    @GetMapping("/welcome")
    public String getGreetings() {
        return "Welcome Biblioteca";
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooksDto = booksService.getAllBooks();
        return ResponseEntity.ok().body(allBooksDto);
    }


    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> filterByStatus(@RequestParam String status) {
        List<BookDto> filteredBooksDto = booksService.filterByStatus(status);
        return ResponseEntity.ok().body(filteredBooksDto);
    }

}
