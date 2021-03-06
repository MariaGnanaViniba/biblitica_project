package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.entity.Books;
import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.entity.CustomerBookMapping;
import com.vapasi.biblioteca.exceptions.BookAlreadyIssuedException;
import com.vapasi.biblioteca.exceptions.BookNotFoundException;
import com.vapasi.biblioteca.exceptions.BookNotIssuedException;
import com.vapasi.biblioteca.exceptions.CustomerNotFoundException;
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



    public BookDto issueBook(CustomerBookMappingDto customerBookMappingDto) {
        doValidations(customerBookMappingDto);

        CustomerBookMapping customerBookMappingEntity = CustomerBookMapping.entityFrom(customerBookMappingDto);
        mappingRepository.save(customerBookMappingEntity);

        BookDto updatedBookDto = updateBookStatus(customerBookMappingEntity.getBookId(), "Checkedout");

        return updatedBookDto;
    }

    private void doValidations(CustomerBookMappingDto customerBookMappingDto) {
        if(!customerRepository.existsByCustomerId(customerBookMappingDto.getCustomerId())){
            throw new CustomerNotFoundException();
        }
        if(!booksRepository.existsById(customerBookMappingDto.getBookId())){
            throw new BookNotFoundException();
        }
        if(booksRepository.existsByIdAndStatus(customerBookMappingDto.getBookId(), "Checkedout")){
            throw new BookAlreadyIssuedException();
        }
    }

    private BookDto updateBookStatus(Integer bookId, String status) {
        Optional<Books> bookEntity = booksRepository.findById(bookId);
        bookEntity.get().setStatus(status);
        Books updatedBook = booksRepository.save(bookEntity.get());
        return BookDto.dtoFrom(updatedBook);
    }

    public List<BookDto> getAllBooks() {
        List<Books> bookEntityList = booksRepository.findAll();
        return convertToBookDtoList(bookEntityList);
    }

    private List<BookDto> convertToBookDtoList(List<Books> bookEntityList) {
        return bookEntityList.stream().map(BookDto::dtoFrom).collect(Collectors.toList());
    }


    public Optional<BookDto> getBookById(Integer id) {
        Optional<Books> bookEntity = booksRepository.findById(id);
        return bookEntity.map(BookDto::dtoFrom);
    }

    public List<BookDto> filterByStatus(String status) {
        List<Books> bookEntityList = booksRepository.findAllByStatusContains(status);
        return convertToBookDtoList(bookEntityList);
    }

    public BookDto returnABook(CustomerBookMappingDto mappingDto) throws Exception {
       CustomerBookMapping mappingEntity =  mappingRepository.findByCustomerIdAndBookId(mappingDto.getCustomerId(),mappingDto.getBookId());
       validateReturnBook(mappingEntity);
       mappingRepository.deleteById(mappingEntity.getCustomerBookMappingId());
        Books bookEntity = booksRepository.findById(mappingDto.getBookId())
                .orElseThrow(() -> {
                    String message = "Book with ID %s doesn't belong to the library."
                            .format(Integer.toString(mappingDto.getBookId()));
                    return new BookNotFoundException(message);
                });

        bookEntity.setStatus("Available");
        Books updatedBook = booksRepository.save(bookEntity);
        return  BookDto.dtoFrom(updatedBook);
    }

    private void validateReturnBook(CustomerBookMapping mappingEntity) {
        if(mappingEntity == null){
            throw new BookNotIssuedException(" Book is not issued to the customer.");
        }
        if(!customerRepository.existsByCustomerId(mappingEntity.getCustomerId())){
            throw new CustomerNotFoundException();
        }
        if(!booksRepository.existsById(mappingEntity.getBookId())){
            throw new BookNotFoundException();
        }

    }
}
