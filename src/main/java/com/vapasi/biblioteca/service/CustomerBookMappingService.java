package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.entity.CustomerBookMappingEntity;
import com.vapasi.biblioteca.repository.CustomerBookMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerBookMappingService {

    private CustomerBookMappingRepository mappingRepository;
    @Autowired
    public CustomerBookMappingService(CustomerBookMappingRepository mappingRepository) {
        this.mappingRepository = mappingRepository;
    }


    public Optional<CustomerBookMappingDto> issueBookToCustomer(CustomerBookMappingDto customerBookMappingDto) {
//        Integer bookId = new Integer(1);
//        Integer customerId = new Integer(1);
//        CustomerBookMappingEntity existingMappingEntity = mappingRepository.findByCustomerId(customerId);
//        if(!mappingRepository.existsByCustomerIdAndBookId(customerId, bookId)){
//            CustomerBookMappingEntity customerBookMappingEntity = new CustomerBookMappingEntity(customerId, bookId);
//            mappingRepository.save(customerBookMappingEntity);
//        }
        return null;
    }
}
