package com.vapasi.biblioteca.libraryDto;

import com.vapasi.biblioteca.entity.BookEntity;

import java.util.Objects;

public class BookDto {

    private Integer id;
    private String isdn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;

    public BookDto(Integer id, String isdn, String title, String author, String publisher, Integer yearOfPublication) {
        this.id = id;
        this.isdn = isdn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getId() {
        return id;
    }

    public String getIsdn() {
        return isdn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return title.equals(bookDto.title) && author.equals(bookDto.author) && publisher.equals(bookDto.publisher) && yearOfPublication.equals(bookDto.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isdn, title, author, publisher, yearOfPublication);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", isdn='" + isdn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

    public static BookDto dtoFrom(BookEntity bookEntity) {
        return new BookDto(bookEntity.getId(),bookEntity.getIsdn(),bookEntity.getTitle(),bookEntity.getAuthor(),bookEntity.getPublisher(),
                bookEntity.getYearOfPublication());
    }
}