package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    boolean existsByCustomerId(Integer customerId);
}
