package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.entity.CustomerBookMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBookMappingRepository extends JpaRepository<CustomerBookMapping, Integer> {
    boolean existsByCustomerIdAndBookId(Integer customerId, Integer bookId);
    CustomerBookMapping findByCustomerId(Integer customerId);
    CustomerBookMapping findByCustomerIdAndBookId(Integer customerId, Integer bookId);
}
