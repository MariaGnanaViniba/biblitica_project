package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import com.vapasi.biblioteca.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {


    private BooksRepository booksRepository;
    private CustomerBookMappingRepository mappingRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public LibraryService(BooksRepository booksRepository, CustomerBookMappingRepository mappingRepository, CustomerRepository customerRepository) {
        this.booksRepository = booksRepository;
        this.mappingRepository = mappingRepository;
        this.customerRepository = customerRepository;
    }



    public Optional<CustomerBookMappingDto> issueBookToCustomer(CustomerBookMappingDto customerBookMappingDto) {

//        CustomerBookMappingEntity existingMappingEntity = mappingRepository.findByCustomerId(customerId);
//        if(!mappingRepository.existsByCustomerIdAndBookId(customerId, bookId)){
//            CustomerBookMappingEntity customerBookMappingEntity = new CustomerBookMappingEntity(customerId, bookId);
//            mappingRepository.save(customerBookMappingEntity);
//        }
// movieEntity.map(MovieDto::dtoFrom);

        CustomerBookMappingEntity customerBookMappingEntity = CustomerBookMappingEntity.entityFrom(customerBookMappingDto);
        CustomerBookMappingDto savedCustomerBookMappingDto = CustomerBookMappingDto.dtoFrom(mappingRepository.save(customerBookMappingEntity));


        updateBookStatus(customerBookMappingEntity.getBookId(), "Checkedout");
        return Optional.of(savedCustomerBookMappingDto);
    }

    private void updateBookStatus(Integer bookId, String status) {
        Optional<BookEntity> bookEntity = booksRepository.findById(bookId);
        bookEntity.get().setStatus(status);
        booksRepository.save(bookEntity.get());
    }

    public List<BookDto> getAllBooks() {
        List<BookEntity> bookEntityList = booksRepository.findAll();
        return convertToBookDtoList(bookEntityList);
    }

    private List<BookDto> convertToBookDtoList(List<BookEntity> bookEntityList) {
        return bookEntityList.stream().map(BookDto::dtoFrom).collect(Collectors.toList());
    }


    public Optional<BookDto> getBookById(Integer id) {
        Optional<BookEntity> bookEntity = booksRepository.findById(id);
        return bookEntity.map(BookDto::dtoFrom);
    }

    public List<BookDto> filterByStatus(String status) {
        List<BookEntity> bookEntityList = booksRepository.findAllByStatusContains(status);
        return convertToBookDtoList(bookEntityList);
    }

}
