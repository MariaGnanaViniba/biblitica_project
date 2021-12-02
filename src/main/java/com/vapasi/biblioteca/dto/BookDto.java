package com.vapasi.biblioteca.dto;

import com.vapasi.biblioteca.entity.BookEntity;

import java.util.Objects;

public class BookDto {

    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;
    private String status;

    public BookDto(Integer id, String isbn, String title, String author, String publisher, Integer yearOfPublication,
                   String status) {
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

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return title.equals(bookDto.title) && author.equals(bookDto.author) && publisher.equals(bookDto.publisher) && yearOfPublication.equals(bookDto.yearOfPublication) && Objects.equals(status, bookDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, publisher, yearOfPublication, status);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", status='" + status + '\'' +
                '}';
    }

    public static BookDto dtoFrom(BookEntity bookEntity) {
        return new BookDto(bookEntity.getId(),bookEntity.getIsbn(),bookEntity.getTitle(),bookEntity.getAuthor(),bookEntity.getPublisher(),
                bookEntity.getYearOfPublication(),bookEntity.getStatus());
    }
}
