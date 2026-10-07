package com.example.banking_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.banking_backend.dto.CustomerResponse;
import com.example.banking_backend.dto.AccountResponse;
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
    public List<CustomerResponse> getAllCustomers(){
        List<Customer> customers = customerService.findAll();

        return customers.stream().map(CustomerResponse::new).toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);

        return new CustomerResponse(customer);
    }

    @PostMapping
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest request){
        Customer customer = customerService.createCustomer(request);

        return new CustomerResponse(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request){
        Customer customer = customerService.updateCustomer(id, request);

        return new CustomerResponse(customer);
    }

    @DeleteMapping ("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}/accounts")
    public List<AccountResponse> getCustomerAccounts(@PathVariable Long id){
        List<Account> accounts = accountService.findAccountsByCustomerId(id);

        return accounts.stream().map(AccountResponse::new).toList();
    }
    
}
