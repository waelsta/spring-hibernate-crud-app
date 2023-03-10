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
    public CustomerDTO findCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map((customer -> mapToCustomerDto(customer))).collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToCustomer(customerDTO);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO){
        Customer customer = mapToCustomer(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToCustomer(customerDTO);
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String query) {
        List<Customer> customers = customerRepository.searchCustomerByFirstNameIsContaining(query);
        return customers.stream().map(customer-> mapToCustomerDto(customer)).collect(Collectors.toList());
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

    public Customer mapToCustomer(CustomerDTO customerDTO){
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .id(customerDTO.getId())
                .build();

        return customer;
    }



}
