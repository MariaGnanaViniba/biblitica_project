package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import com.vapasi.biblioteca.repository.BooksRepository;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {


    private BooksRepository booksRepository;
    private CustomerBookMappingRepository mappingRepository;

    @Autowired
    public LibraryService(BooksRepository booksRepository, CustomerBookMappingRepository mappingRepository) {
        this.booksRepository = booksRepository;
        this.mappingRepository = mappingRepository;
    }



    public Optional<CustomerBookMappingDto> issueBookToCustomer(CustomerBookMappingDto customerBookMappingDto) {
//        Integer bookId = new Integer(1);
//        Integer customerId = new Integer(1);
//        CustomerBookMappingEntity existingMappingEntity = mappingRepository.findByCustomerId(customerId);
//        if(!mappingRepository.existsByCustomerIdAndBookId(customerId, bookId)){
//            CustomerBookMappingEntity customerBookMappingEntity = new CustomerBookMappingEntity(customerId, bookId);
//            mappingRepository.save(customerBookMappingEntity);
//        }
// movieEntity.map(MovieDto::dtoFrom);
        CustomerBookMappingEntity customerBookMappingEntity = CustomerBookMappingEntity.entityFrom(customerBookMappingDto);
        CustomerBookMappingDto savedCustomerBookMappingDto = CustomerBookMappingDto.dtoFrom(mappingRepository.save(customerBookMappingEntity));
        return Optional.of(savedCustomerBookMappingDto);
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
