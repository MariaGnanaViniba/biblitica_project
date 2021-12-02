package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository <BookEntity, Integer> {
    boolean existsById(Integer id);
    Optional<BookEntity> findById(Integer id);
    List<BookEntity> findAllByStatusContains(String status);

}
