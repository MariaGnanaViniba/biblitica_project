package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    boolean existsByCustomerId(Integer customerId);
}
