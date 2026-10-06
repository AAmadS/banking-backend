package com.example.banking_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.banking_backend.dto.CreateCustomerRequest;
import com.example.banking_backend.dto.UpdateCustomerRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Customer;
import com.example.banking_backend.service.AccountService;
import com.example.banking_backend.service.CustomerService;

import java.util.List;

//Tells Spring this class handles HTTP requests and its returned objects should 
//become response data such as JSON
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerController(CustomerService customerService, AccountService accountService){
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request){
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping ("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getCustomerAccounts(@PathVariable Long id){
        return accountService.findAccountsByCustomerId(id);
    }
    
}
