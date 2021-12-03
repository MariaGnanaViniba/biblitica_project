package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.exceptions.BookAlreadyIssuedException;
import com.vapasi.biblioteca.exceptions.BookNotFoundException;
import com.vapasi.biblioteca.exceptions.CustomerNotFoundException;
import com.vapasi.biblioteca.exceptions.ErrorMessagesEnum;
import com.vapasi.biblioteca.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/library")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}
)
@Api(value="To handle operations for Online Library Management System")
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

    @ApiOperation(value = "View a list of all the books in the library")
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooksDto = libraryService.getAllBooks();
        return ResponseEntity.ok().body(allBooksDto);
    }


    @ApiOperation(value = "View a list of Available books in the library")
    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<List<BookDto>> filterByStatus(@RequestParam String status) {
        List<BookDto> filteredBooksDto = libraryService.filterByStatus(status);
        return ResponseEntity.ok().body(filteredBooksDto);
    }

    @ApiOperation(value = "API - To issue a Book ", response = String.class)
    @PostMapping("/books/issue")
    //@PutMapping("/books/{id}/issue")
    //private ResponseEntity<String> issueBookToCustomer(@PathVariable Integer id, @RequestBody CustomerBookMappingDto mappingDto) {
    private ResponseEntity<String> issueBook(@RequestBody CustomerBookMappingDto mappingDto) {

        if(mappingDto.getCustomerId() == null ||
                mappingDto.getBookId() == null) {
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.ImproperDetails.getMessage());
        }
        try {
            BookDto updatedBookDto = libraryService.issueBook(mappingDto);
            String message = String.format("The book %s is issued successfully !", updatedBookDto.getTitle());
            return ResponseEntity.ok().body(message);
        }catch(CustomerNotFoundException custException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.CustomerNotFound.getMessage());
        }catch(BookNotFoundException bookException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.BookNotFound.getMessage());
        }catch(BookAlreadyIssuedException bookException){
            return ResponseEntity.badRequest().body(ErrorMessagesEnum.BookAlreadyIssued.getMessage());
        }
    }


    @ApiOperation(value = "API - To return a Book", response = String.class)
    @PutMapping ("/books/return")
    private ResponseEntity<String> returnABook(@RequestBody CustomerBookMappingDto mappingDto) {
        try {
            BookDto updatedBookDto = libraryService.returnABook(mappingDto);
            String message = String.format("The book %s is returned successfully !", updatedBookDto.getTitle());
            return ResponseEntity.ok().body(message);
        }
        catch(BookNotFoundException bookException) {
            return ResponseEntity.badRequest().body(bookException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
