package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BooksServiceTest {

    BooksService booksService;

    BooksRepository booksRepository;


    @BeforeEach
    void setUp() {
        booksRepository = mock(BooksRepository.class);
        booksService = new BooksService(booksRepository);
    }

    @Test
    public void getAllMoviesAddedToList() {
        List<BookEntity> allBooks = new ArrayList<>();
        allBooks.add(new BookEntity(112, "ME2321", "Refractorin", "Martin","publisher",2000));
        allBooks.add(new BookEntity(104, "p356", "Samuel Story", "Quintine","publisher2",1980));
        when(booksRepository.findAll()).thenReturn(allBooks);

        List<BookDto> actualBooks = booksService.getAllBooks();
        List<BookDto> expectedBooks = Arrays.asList(
                new BookDto(112, "ME2321", "Refractorin", "Martin","publisher",2000),
                new BookDto(104, "p356", "Samuel Story", "Quintine","publisher2",1980));

        verify(booksRepository, times(1)).findAll();
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getEmptyWhenNoBooksAddedToList() {
        List<BookEntity> allBooks = new ArrayList<>();
        when(booksRepository.findAll()).thenReturn(allBooks);

        List<BookDto> totalBooks = booksService.getAllBooks();

        verify(booksRepository, times(1)).findAll();
        assertEquals(allBooks.size(), totalBooks.size());
    }

    @Test
    void shouldGetBookById() {
        // Given
        BookEntity bookEntity = new BookEntity(112, "ME2321", "Refractorin", "Martin","publisher",2000);
        when(booksRepository.findById(112)).thenReturn(Optional.of(bookEntity));

        // When
        Optional<BookDto> actualBook = booksService.getBookById(112);
        BookDto expectedBook = new BookDto(112, "ME2321", "Refractorin",
                "Martin","publisher",2000);

        // Then
        verify(booksRepository, times(1)).findById(112);
        assertEquals(expectedBook, actualBook.get());
    }
}
