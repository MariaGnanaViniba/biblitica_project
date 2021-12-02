package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.exceptions.BookNotFoundException;
import com.vapasi.biblioteca.exceptions.CustomerNotFoundException;
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
    private LibraryController(LibraryService libraryService) {

        this.libraryService = libraryService;
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

    @PostMapping("/books/")
    private ResponseEntity<String> issueBookToCustomer(@RequestBody CustomerBookMappingDto mappingDto) {
        if(mappingDto.getCustomerId() == null ||
                mappingDto.getBookId() == null) {
            return ResponseEntity.badRequest().body("Please provide proper details, try again.");
        }
        try {
            Optional<CustomerBookMappingDto> savedMappingDto = libraryService.issueBook(mappingDto);
            if (savedMappingDto.isPresent()) {
                return ResponseEntity.ok().body("Book is successful issued.");
            }
        }catch(CustomerNotFoundException custException){
            return ResponseEntity.badRequest().body("Customer not found, please try again.");
        }catch(BookNotFoundException bookException){
            return ResponseEntity.badRequest().body("Book not found, please try again.");
        }
        return ResponseEntity.notFound().build();
    }
}
