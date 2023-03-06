package com.example.mvccrud.service;

import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.service.impl.CustomerServiceImpl;

import java.util.List;

public interface CustomerService {

    public List<CustomerDTO> getAllCustomers();


}
