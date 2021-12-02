package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.dto.CustomerBookMappingDto;
import com.vapasi.biblioteca.service.CustomerBookMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/customerbooks")
public class CustomerController {
    CustomerBookMappingService mappingService;

    @PostMapping("/")
    private ResponseEntity<String> issueBookToCustomer(@RequestBody CustomerBookMappingDto mappingDto) {
        System.out.println(mappingDto.getCustomerId());
        System.out.println(mappingDto.getBookId());
        Optional<CustomerBookMappingDto> savedMappingDto = mappingService.issueBookToCustomer(mappingDto);

        if (savedMappingDto.isPresent()) {

            return ResponseEntity.ok().body("Successful");
        }
        return ResponseEntity.notFound().build();

    }
}
