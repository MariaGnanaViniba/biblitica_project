package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.BookEntity;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBookMappingRepository extends JpaRepository<CustomerBookMappingEntity, Integer> {
    boolean existsByCustomerIdAndBookId(Integer customerId, Integer bookId);
    CustomerBookMappingEntity findByCustomerId(Integer customerId);
    CustomerBookMappingEntity findByCustomerIdAndBookId(Integer customerId, Integer bookId);
}
