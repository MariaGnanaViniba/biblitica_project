package com.vapasi.biblioteca.dto;

import com.vapasi.biblioteca.entity.CustomerBookMapping;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class CustomerBookMappingDto {
    @ApiModelProperty(notes = "The database generated mapping ID")
    private Integer customerBookMappingId;
    @ApiModelProperty(notes = "Customer ID")
    private Integer customerId;
    @ApiModelProperty(notes = "Book ID")
    private Integer bookId;

    public Integer getCustomerBookMappingId() {
        return customerBookMappingId;
    }

    public CustomerBookMappingDto(Integer customerBookMappingId, Integer customerId, Integer bookId) {
        this.customerBookMappingId = customerBookMappingId;
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerBookMappingDto that = (CustomerBookMappingDto) o;
        return customerBookMappingId.equals(that.customerBookMappingId) && customerId.equals(that.customerId) && bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerBookMappingId, customerId, bookId);
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

    public static CustomerBookMappingDto dtoFrom(CustomerBookMapping mappingEntity) {
        return new CustomerBookMappingDto(mappingEntity.getCustomerBookMappingId(),mappingEntity.getCustomerId(),
                mappingEntity.getBookId());
    }
}
