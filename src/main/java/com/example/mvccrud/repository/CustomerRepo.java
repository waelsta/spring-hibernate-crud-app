package com.example.mvccrud.repository;

import com.example.mvccrud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer, Long> {

     List<Customer> findAll();

     Customer save(Customer customer);

}
