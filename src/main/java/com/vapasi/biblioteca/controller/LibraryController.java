package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.exceptions.BookAlreadyIssuedException;
import com.vapasi.biblioteca.exceptions.BookNotFoundException;
import com.vapasi.biblioteca.exceptions.CustomerNotFoundException;
import com.vapasi.biblioteca.exceptions.ErrorMessagesEnum;
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

    @PostMapping("/books/issue")
    private ResponseEntity<String> issueBookToCustomer(@RequestBody CustomerBookMappingDto mappingDto) {
        if(mappingDto.getCustomerId() == null ||
                mappingDto.getBookId() == null) {
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.ImproperDetails.getMessage());
        }
        try {
            Optional<CustomerBookMappingDto> savedMappingDto = libraryService.issueBook(mappingDto);
            if (savedMappingDto.isPresent()) {
                return ResponseEntity.ok().body(ErrorMessagesEnum.BookSuccessfullyIssued.getMessage());
            }
        }catch(CustomerNotFoundException custException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.CustomerNotFound.getMessage());
        }catch(BookNotFoundException bookException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.BookNotFound.getMessage());
        }catch(BookAlreadyIssuedException bookException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.BookAlreadyIssued.getMessage());
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping ("/books/return")
    private ResponseEntity<String> returnABook(@RequestBody CustomerBookMappingDto mappingDto) {
        //System.out.println("Book id to Return: " + id);
        BookDto updatedBookDto = libraryService.returnABook(mappingDto);
        StringBuilder message = new StringBuilder("The Book ");
        message.append(updatedBookDto.getTitle()).append(" is returned Successfully!");
        return ResponseEntity.ok().body(message.toString());
    }
}
