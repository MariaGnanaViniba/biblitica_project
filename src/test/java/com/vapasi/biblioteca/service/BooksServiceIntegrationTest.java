package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BooksServiceIntegrationTest {

    @Autowired
    BooksService booksService;
    @Autowired
    BooksRepository booksRepository;

    @Test
    public void getAllBooksAddedToList() {
        //Given
       booksRepository.deleteAll();

        // When
        List<BookEntity> allBooks = new ArrayList<>();
        allBooks.add(new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000, "Available"));
        allBooks.add(new BookEntity(null, "p356", "Samuel Story", "Quintine","publisher2",1980,"Available"));
        booksRepository.saveAll(allBooks);

        // Then
        List<BookDto> actualBooks = booksService.getAllBooks();
        assertEquals(2, actualBooks.size());
    }


    @Test
    public void getEmptyBooksWhenNoBooksAddedToList() {
        //Given
        booksRepository.deleteAll();

        // When
        List<BookDto> allBooks = booksService.getAllBooks();

        // Then
        assertEquals(0, allBooks.size());
    }


    @Test
    void shouldSaveAndRetrieveBook() {
        // Given
        booksRepository.deleteAll();

        // When
        BookEntity bookEntity = new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000,"Available");
        BookEntity savedEntity = booksRepository.save(bookEntity);

        // Then
        BookDto expectedBook = new BookDto(savedEntity.getId() ,savedEntity.getIsbn(),savedEntity.getTitle(),savedEntity.getAuthor(),savedEntity.getPublisher(),savedEntity.getYearOfPublication(),savedEntity.getStatus());
        assertEquals(expectedBook, booksService.getBookById(savedEntity.getId()).get());
    }

    @Test
    public void filterBooksByStatus() {
        // Given
        booksRepository.deleteAll();

        // When
        List<BookEntity> allBooks = new ArrayList<>();
        allBooks.add(new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000,"Available"));
        allBooks.add(new BookEntity(null, "p356", "Samuel Story", "Quintine","publisher2",1980, "Available"));
        booksRepository.saveAll(allBooks);

        //Then
        List<BookDto> actualBooks = booksService.filterByStatus("Available");
        assertEquals(2, actualBooks.size());
    }
    @GetMapping("/issueBook")
    private ResponseEntity<String> getIssueBook(@RequestParam(value="bookId") String bookId, @RequestParam(value="customerId") String customerId) {
        List<BookDto> bookDto = booksService.getIssueBookToCustomer(bookId, customerId);
        /*
        if (movie.size() != 0) {

            return ResponseEntity.ok().body(movie);
        }

         */
//        return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body("");
    }
}
