package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/library")
public class LibraryController {

    LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService booksService) {

        this.libraryService = booksService;
    }

    @GetMapping("/welcome")
    public String getGreetings() {
        return "Welcome Biblioteca";
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooksDto = libraryService.getAllBooks();
        return ResponseEntity.ok().body(allBooksDto);
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> filterByStatus(@RequestParam String status) {
        List<BookDto> filteredBooksDto = libraryService.filterByStatus(status);
        return ResponseEntity.ok().body(filteredBooksDto);
    }

    @PostMapping("/books")
    private ResponseEntity<String> issueBookToCustomer(@RequestBody CustomerBookMappingDto mappingDto) {
        System.out.println(mappingDto.getCustomerId());
        System.out.println(mappingDto.getBookId());
        Optional<CustomerBookMappingDto> savedMappingDto = libraryService.issueBookToCustomer(mappingDto);

        if (savedMappingDto.isPresent()) {

            return ResponseEntity.ok().body("Successful");
        }
        return ResponseEntity.notFound().build();

    }
}
