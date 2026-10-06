package com.example.banking_backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.CustomerRepository;
import com.example.banking_backend.dto.CreateCustomerRequest;
import com.example.banking_backend.dto.UpdateCustomerRequest;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.model.Customer;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public Customer createCustomer(CreateCustomerRequest request) {

        try {
            if (customerRepository.existsByEmail(request.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already in use");
            }

            if (customerRepository.existsByPhone(request.getPhone())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This phone number is already in use");
            }

            Customer newCustomer = new Customer(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPhone());

            return customerRepository.save(newCustomer);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Customer updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer existingCustomer = findById(id);

        if (customerRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already in use");
        }

        if (customerRepository.existsByPhoneAndIdNot(request.getPhone(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This phone number is already in use");
        }

        existingCustomer.updateContactInfo(request.getEmail(), request.getPhone());

        return customerRepository.save(existingCustomer);

    }

    public void deleteCustomer(Long id){
        Customer existingCustomer = findById(id);

        if (accountRepository.existsByCustomerIdAndAccountStatusNot(id, AccountStatus.CLOSED)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer cannot be deleted while an account is OPEN"); 
        }

        customerRepository.delete(existingCustomer);

    }
}
