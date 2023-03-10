package com.example.mvccrud.service;

import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.model.Customer;

import java.util.List;

public interface CustomerService {

    public CustomerDTO findCustomerById(long customerId);

    public List<CustomerDTO> getAllCustomers();

    public Customer saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customer);

    void deleteCustomer(CustomerDTO customer);


}
