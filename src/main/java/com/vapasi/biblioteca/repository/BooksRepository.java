package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository <BookEntity, Integer> {
    List<BookEntity> findAllByStatusContains(String status);

}
