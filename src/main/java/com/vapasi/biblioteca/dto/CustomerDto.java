package com.vapasi.biblioteca.dto;


import java.util.Objects;

public class CustomerDto {
    private Integer customerid;
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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }
}
