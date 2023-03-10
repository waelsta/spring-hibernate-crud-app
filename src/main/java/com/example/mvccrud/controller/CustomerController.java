package com.example.mvccrud.controller;


import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.model.Customer;
import com.example.mvccrud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model){
        model.addAttribute("customers",customerService.getAllCustomers());
        return "customers-list";
    }

    @GetMapping("/")
    public String landingPage(){return "landing";}

    @GetMapping("/customers/create")
    public String createNewCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "create-customer";
    }

    @PostMapping("/customers/create")
    public String saveCustomer(@Valid @ModelAttribute(value="customer") CustomerDTO customerDTO,
                               BindingResult result,Model model){

        if(result.hasErrors()){
            model.addAttribute("customer",customerDTO);
            return "create-customer";
        }
        customerService.saveCustomer(customerDTO);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}/edit")
    public String editCustomer(@PathVariable("customerId") long customerId,Model model){
        CustomerDTO customer = customerService.findCustomerById(customerId);
        model.addAttribute("customer",customer);
        return "customers-edit";
    }

    @PostMapping("/customers/{customerId}/edit")
    public String updateCustomer(@PathVariable("customerId") long customerId, @Valid @ModelAttribute("customer") CustomerDTO customerDTO,
                                 BindingResult result){
        if(result.hasErrors()) return "customers-edit";
        customerDTO.setId(customerId);
        customerService.updateCustomer(customerDTO);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}")
    public String showCustomer(@PathVariable("customerId") long customerId, Model model){
        CustomerDTO customer = customerService.findCustomerById(customerId);
        model.addAttribute("customer",customer);
        return "customer-details";
    }

    @GetMapping("/customers/{customerId}/delete")
    public String deleteCustomer(@PathVariable("customerId") long customerId){
        customerService.deleteCustomer(customerService.findCustomerById(customerId));
        return "redirect:/customers";
    }

    @GetMapping("/customers/search")
    public String searchCustomers(@RequestParam(value="query") String query, Model model){
        List<CustomerDTO> customers = customerService.searchCustomers(query);
        model.addAttribute("customers",customers);
        return "customers-list";
    }

}
