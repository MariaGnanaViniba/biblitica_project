package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.libraryDto.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {

    public List<BookDto> getAllBooks() {
        List<BookDto> bookDto = new ArrayList<>();

    /*        for (BookDto BookEntity : movieRepository.findAll()) {
        movieDto.add(BookDto.dtoFrom(movieEntity));
    }*/
        return bookDto;
    }
}
