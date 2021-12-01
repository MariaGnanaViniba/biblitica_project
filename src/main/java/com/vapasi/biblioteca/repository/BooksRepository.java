package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository <BookEntity, Integer> {
    //BookEntity[] findByTitle(String title);
}
