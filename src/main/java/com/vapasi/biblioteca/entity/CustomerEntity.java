package com.vapasi.biblioteca.entity;

import com.vapasi.biblioteca.dto.CustomerDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String email;
    private String phone;

    public CustomerEntity() {}

    public CustomerEntity(Integer customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerId.equals(that.customerId) && customerName.equals(that.customerName) && email.equals(that.email) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, email, phone);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public static CustomerEntity entityFrom(CustomerDto customerDto) {
        return new CustomerEntity(customerDto.getCustomerid(), customerDto.getCustomerName());
    }
}
