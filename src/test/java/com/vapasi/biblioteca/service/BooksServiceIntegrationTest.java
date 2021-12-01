package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
       booksRepository.deleteAll();

        List<BookEntity> allBooks = new ArrayList<>();
        allBooks.add(new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000));
        allBooks.add(new BookEntity(null, "p356", "Samuel Story", "Quintine","publisher2",1980));
        booksRepository.saveAll(allBooks);

        List<BookDto> actualBooks = booksService.getAllBooks();
        assertEquals(2, actualBooks.size());
    }


    @Test
    public void getEmptyBooksWhenNoBooksAddedToList() {
        booksRepository.deleteAll();

        List<BookDto> allBooks = booksService.getAllBooks();

        assertEquals(0, allBooks.size());
    }


    @Test
    void shouldSaveAndRetrieveBook() {
        // Given
        booksRepository.deleteAll();

        // When
        BookEntity bookEntity = new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000);
        BookEntity savedEntity = booksRepository.save(bookEntity);

        // Then
        BookDto expectedBook = new BookDto(savedEntity.getId() ,savedEntity.getIsbn(),savedEntity.getTitle(),savedEntity.getAuthor(),savedEntity.getPublisher(),savedEntity.getYearOfPublication());
        assertEquals(expectedBook, booksService.getBookById(savedEntity.getId()).get());
    }
}
