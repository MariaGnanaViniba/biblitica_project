package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.libraryDto.BookDto;
import com.vapasi.biblioteca.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksService {


    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
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
}
