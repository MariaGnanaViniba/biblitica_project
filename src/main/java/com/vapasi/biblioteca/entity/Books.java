package com.vapasi.biblioteca.entity;

import com.vapasi.biblioteca.dto.BookDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;
    private String status;

    public Books() {}
    public Books(Integer id, String isbn, String title, String author, String publisher, Integer yearOfPublication, String status) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books that = (Books) o;
        return Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(publisher, that.publisher) && Objects.equals(yearOfPublication, that.yearOfPublication) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, publisher, yearOfPublication, status);
    }

    public static Books entityFrom(BookDto bookDto) {
        return new Books(bookDto.getId(), bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor(),bookDto.getPublisher(),bookDto.getYearOfPublication(), bookDto.getStatus());
    }

}
