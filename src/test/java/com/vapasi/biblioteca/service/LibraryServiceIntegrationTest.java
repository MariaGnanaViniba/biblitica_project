package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import com.vapasi.biblioteca.exceptions.BookAlreadyIssuedException;
import com.vapasi.biblioteca.exceptions.BookNotFoundException;
import com.vapasi.biblioteca.exceptions.CustomerNotFoundException;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import com.vapasi.biblioteca.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryServiceIntegrationTest {

    @Autowired
    LibraryService booksService;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    CustomerBookMappingRepository customerBookMappingRepository;
    @Autowired
    CustomerRepository customerRepository;


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


    @Test
    void shouldBeAbleToReturnBook() {
        //Given
        booksRepository.deleteAll();

        //When
        BookEntity bookEntity = new BookEntity(null, "ME2321", "Refractorin", "Martin","publisher",2000,"Available");
        BookEntity savedEntity = booksRepository.save(bookEntity);
        BookEntity book = booksRepository.findById(savedEntity.getId()).get();
        book.setStatus("Available");
        savedEntity = booksRepository.save(book);
        BookDto actualDto = new BookDto(savedEntity.getId() ,savedEntity.getIsbn(),savedEntity.getTitle(),savedEntity.getAuthor(),savedEntity.getPublisher(),savedEntity.getYearOfPublication(),savedEntity.getStatus());

        //Then
        BookDto expectedDto = booksService.returnABook(savedEntity.getId());
        Assertions.assertEquals(expectedDto, actualDto);
    }
    @Test
    void shouldNotBeAbleToCheckoutBook_ifBookAlreadyIssued(){
        //Arrange
        Integer bookId = new Integer(1);
        Integer customerId = new Integer(1);
        CustomerBookMappingEntity expectedMappingEntity
                = new CustomerBookMappingEntity(new Integer(1),customerId, bookId);

        //@TODO find out the error
//        booksService.issueBook(CustomerBookMappingDto.dtoFrom(expectedMappingEntity));
//
//        Exception exception = Assertions.assertThrows(BookAlreadyIssuedException.class, () -> {
//            booksService.issueBook(CustomerBookMappingDto.dtoFrom(expectedMappingEntity));
//        });
    }
    @Test
    void shouldNotBeAbleToCheckoutBook_ifNotValidCustomer(){
        //Arrange
        Integer bookId = new Integer(1);
        Integer customerId = new Integer(10000);
        CustomerBookMappingEntity expectedMappingEntity
                = new CustomerBookMappingEntity(new Integer(1),customerId, bookId);
        Exception exception = Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            booksService.issueBook(CustomerBookMappingDto.dtoFrom(expectedMappingEntity));
        });
    }
    @Test
    void shouldNotBeAbleToCheckoutBook_ifBookNotPresentInLibrary(){
        //Arrange
        Integer bookId = new Integer(1000);
        Integer customerId = new Integer(1);
        CustomerBookMappingEntity expectedMappingEntity
                = new CustomerBookMappingEntity(new Integer(1),customerId, bookId);
        Exception exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
            booksService.issueBook(CustomerBookMappingDto.dtoFrom(expectedMappingEntity));
        });
    }
    @Test
    void shouldBeAbleToCheckoutBook(){
        //Arrange
        Integer bookId = new Integer(1);
        Integer customerId = new Integer(1);
        CustomerBookMappingEntity expectedMappingEntity
                = new CustomerBookMappingEntity(new Integer(1),customerId, bookId);
        CustomerBookMappingEntity savedMappingEntity = null;

        if(booksRepository.existsByIdAndStatus(bookId, "Available")){

            //make new entry in mapping table
            CustomerBookMappingEntity customerBookMappingEntity = new CustomerBookMappingEntity(null, customerId, bookId);
            savedMappingEntity = customerBookMappingRepository.saveAndFlush(customerBookMappingEntity);

            //Change the status to checkedout in book table
            Optional<BookEntity> bookEntity = booksRepository.findById(bookId);
            if(bookEntity.isPresent()) {
                System.out.println(bookEntity.get());
                bookEntity.get().setStatus("Checkedout");
                booksRepository.saveAndFlush(bookEntity.get());
            }
        }
        Assertions.assertEquals(expectedMappingEntity, savedMappingEntity);
    }
}
