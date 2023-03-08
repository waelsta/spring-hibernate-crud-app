package com.example.mvccrud.controller;


import com.example.mvccrud.dto.CustomerDTO;
import com.example.mvccrud.model.Customer;
import com.example.mvccrud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
}
