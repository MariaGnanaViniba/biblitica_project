package com.vapasi.biblioteca.dto;


import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class CustomerDto {

    @ApiModelProperty(notes = "The database generated mapping ID")
    private Integer customerid;
    @ApiModelProperty(notes = "Customer Name")
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public CustomerDto(Integer customerid, String customerName) {
        this.customerid = customerid;
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return customerid.equals(that.customerid) && customerName.equals(that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerid, customerName);
    }

    public Integer getCustomerid() {
        return customerid;
    }

}
