package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.Books;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import com.vapasi.biblioteca.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    LibraryService booksService;

    BooksRepository booksRepository;
    CustomerBookMappingRepository mappingRepository;
    CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        booksRepository = mock(BooksRepository.class);
        booksService = new LibraryService(booksRepository, mappingRepository, customerRepository);
    }

    @Test
    public void getAllMoviesAddedToList() {
        // Given
        List<Books> allBooks = new ArrayList<>();
        allBooks.add(new Books(112, "ME2321", "Refractorin", "Martin","publisher",2000,"Available"));
        allBooks.add(new Books(104, "p356", "Samuel Story", "Quintine","publisher2",1980,"Available"));
        when(booksRepository.findAll()).thenReturn(allBooks);

        // When
        List<BookDto> actualBooks = booksService.getAllBooks();
        List<BookDto> expectedBooks = Arrays.asList(
                new BookDto(112, "ME2321", "Refractorin", "Martin","publisher",2000,"Available"),
                new BookDto(104, "p356", "Samuel Story", "Quintine","publisher2",1980,"Available"));

        // Then
        verify(booksRepository, times(1)).findAll();
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getEmptyWhenNoBooksAddedToList() {
        // Given
        List<Books> allBooks = new ArrayList<>();
        when(booksRepository.findAll()).thenReturn(allBooks);

        // When
        List<BookDto> totalBooks = booksService.getAllBooks();

        // Then
        verify(booksRepository, times(1)).findAll();
        assertEquals(allBooks.size(), totalBooks.size());
    }

    @Test
    void shouldGetBookById() {
        // Given
        Books bookEntity = new Books(112, "ME2321", "Refractorin", "Martin","publisher",2000,"Available");
        when(booksRepository.findById(112)).thenReturn(Optional.of(bookEntity));

        // When
        Optional<BookDto> actualBook = booksService.getBookById(112);
        BookDto expectedBook = new BookDto(112, "ME2321", "Refractorin",
                "Martin","publisher",2000,"Available");

        // Then
        verify(booksRepository, times(1)).findById(112);
        assertEquals(expectedBook, actualBook.get());
    }


    @Test
    void shouldAbleToFilterBookByStatus() {
        // Given
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books(112, "ME2321", "Refractorin", "Martin","publisher",2000,"Available"));
        booksList.add(new Books(104, "p356", "Samuel Story", "Quintine","publisher2",1980,"Available"));
        when(booksRepository.findAllByStatusContains("Available")).thenReturn(booksList);

        // When
        List<BookDto> actualBooks = booksService.filterByStatus("Available");
        List<BookDto> expectedBooks = Arrays.asList(
                new BookDto(112, "ME2321", "Refractorin", "Martin","publisher",2000,"Available"),
                new BookDto(104, "p356", "Samuel Story", "Quintine","publisher2",1980,"Available"));

        // Then
        verify(booksRepository, times(1)).findAllByStatusContains("Available");
        assertEquals(expectedBooks, actualBooks);
    }
}
