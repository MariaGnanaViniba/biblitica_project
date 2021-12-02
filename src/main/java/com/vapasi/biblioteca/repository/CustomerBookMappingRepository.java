package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerBookMappingRepository extends CrudRepository<CustomerBookMappingEntity, Integer> {
    boolean existsByCustomerIdAndBookId(Integer customerId, Integer bookId);
    CustomerBookMappingEntity findByCustomerId(Integer customerId);
}
