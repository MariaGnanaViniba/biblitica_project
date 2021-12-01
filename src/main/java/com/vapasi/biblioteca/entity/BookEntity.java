package com.vapasi.biblioteca.entity;

import com.vapasi.biblioteca.dto.BookDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;

    public BookEntity() {}
    public BookEntity(Integer id, String isbn, String title, String author, String publisher, Integer yearOfPublication) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return title.equals(that.title) && author.equals(that.author) && publisher.equals(that.publisher) && yearOfPublication.equals(that.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, publisher, yearOfPublication);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

    public static BookEntity entityFrom(BookDto bookDto) {
        return new BookEntity(null, bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor(),bookDto.getPublisher(),bookDto.getYearOfPublication());
    }

}
