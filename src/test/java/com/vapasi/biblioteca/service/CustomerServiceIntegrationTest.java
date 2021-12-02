package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTest {

    @Autowired
    LibraryService booksService;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    CustomerBookMappingRepository customerBookMappingRepository;


    @Test
    void shouldBeAbleToCheckoutBook(){
        //Arrange
        Integer bookId = new Integer(1);
        Integer customerId = new Integer(1);
        CustomerBookMappingEntity expectedMappingEntity
                = new CustomerBookMappingEntity(new Integer(1),customerId, bookId);
        CustomerBookMappingEntity savedMappingEntity = null;
        if(!customerBookMappingRepository.existsByCustomerIdAndBookId(customerId, bookId)){
            CustomerBookMappingEntity customerBookMappingEntity = new CustomerBookMappingEntity(null, customerId, bookId);
            savedMappingEntity = customerBookMappingRepository.save(customerBookMappingEntity);
        }
        Assertions.assertEquals(expectedMappingEntity, savedMappingEntity);
        //issueBook?bookid=1&customerid=1

        // check if the book has status = avilable
        //BookEntity bookEntity = new BookEntity(new Integer(1),null,null,null);
       // if(booksRepository.existsById(bookEntity.getId())){

            //Change the status to checkedout in book table

            //make new entry in mapping table
        //}
    }
    @Test
    void shouldBeAbleToReturnBook() {
        //returnBook?bookid=1&customerid=1
    }
}
