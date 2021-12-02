package com.vapasi.biblioteca.dto;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;

import java.util.Objects;

public class CustomerBookMappingDto {
    private Integer customerBookMappingid;
    private Integer customerId;
    private Integer bookId;
    public Integer getCustomerBookMappingid() {
        return customerBookMappingid;
    }

    public CustomerBookMappingDto(Integer customerBookMappingid, Integer customerId, Integer bookId) {
        this.customerBookMappingid = customerBookMappingid;
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public void setCustomerBookMappingid(Integer customerBookMappingid) {
        this.customerBookMappingid = customerBookMappingid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerBookMappingDto that = (CustomerBookMappingDto) o;
        return customerBookMappingid.equals(that.customerBookMappingid) && customerId.equals(that.customerId) && bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerBookMappingid, customerId, bookId);
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

    public static CustomerBookMappingDto dtoFrom(CustomerBookMappingEntity mappingEntity) {
        return new CustomerBookMappingDto(mappingEntity.getCustomerBookMappingId(),mappingEntity.getCustomerId(),
                mappingEntity.getBookId());
    }
}
