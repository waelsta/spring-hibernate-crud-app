package com.example.mvccrud.service.impl;

import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.model.Customer;
import com.example.mvccrud.repository.CustomerRepo;
import com.example.mvccrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepo customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map((customer -> mapToCustomerDto(customer))).collect(Collectors.toList());
    }


    public CustomerDTO mapToCustomerDto(Customer customer){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();

        return customerDTO;
    }




}
