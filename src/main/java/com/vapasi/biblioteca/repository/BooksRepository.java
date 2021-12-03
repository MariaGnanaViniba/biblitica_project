package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository <Books, Integer> {
    boolean existsById(Integer id);
    Optional<Books> findById(Integer id);
    List<Books> findAllByStatusContains(String status);
    boolean existsByIdAndStatus(Integer id, String status);
}
