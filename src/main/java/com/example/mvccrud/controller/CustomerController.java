package com.example.mvccrud.controller;


import com.example.mvccrud.model.Customer;
import com.example.mvccrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String saveCustomer(@ModelAttribute(value="customer") Customer customer){
        customerService.saveCustomer(customer);
        return "customers-list";
    }
}
