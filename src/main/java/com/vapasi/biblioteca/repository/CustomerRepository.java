package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
}
