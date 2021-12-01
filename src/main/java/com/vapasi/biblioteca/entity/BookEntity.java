package com.vapasi.biblioteca.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isdn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;

    public BookEntity() {}
    public BookEntity(Integer id, String isdn, String title, String author, String publisher, Integer yearOfPublication) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
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
        return Objects.hash(id, isdn, title, author, publisher, yearOfPublication);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", isdn='" + isdn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
