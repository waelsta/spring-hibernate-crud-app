package com.example.mvccrud.repository;

import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer, Long> {

     List<Customer> findAll();

     Customer save(Customer customer);

     void delete(Customer customer);

     @Query("SELECT c FROM Customer c WHERE c.lastName LIKE CONCAT('%',:query,'%') ")
     List<Customer> searchCustomers(String query);

     List<Customer> searchCustomerByFirstNameIsContaining(String query);

}
