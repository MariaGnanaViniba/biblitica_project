package com.vapasi.biblioteca.entity;

import com.vapasi.biblioteca.dto.BookDto;
import com.vapasi.biblioteca.dto.CustomerBookMappingDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="CustomerBookMapping")
public class CustomerBookMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerBookMappingId;
    private Integer customerId;
    private Integer bookId;
    public CustomerBookMappingEntity() {}
    public Integer getCustomerBookMappingId() {
        return customerBookMappingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerBookMappingEntity that = (CustomerBookMappingEntity) o;
        return customerBookMappingId.equals(that.customerBookMappingId) && customerId.equals(that.customerId) && bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerBookMappingId, customerId, bookId);
    }

    public CustomerBookMappingEntity(Integer customerBookMappingId, Integer customerId, Integer bookId) {
        this.customerBookMappingId = customerBookMappingId;
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public void setCustomerBookMappingId(Integer customerBookMappingId) {
        this.customerBookMappingId = customerBookMappingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public static CustomerBookMappingEntity entityFrom(CustomerBookMappingDto customerBookMappingDto) {
        return new CustomerBookMappingEntity(null,customerBookMappingDto.getCustomerId(), customerBookMappingDto.getBookId());
    }
}
