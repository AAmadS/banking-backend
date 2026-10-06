package com.example.banking_backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_backend.dto.CreateAccountRequest;
import com.example.banking_backend.dto.UpdateAccountStatusRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.model.Customer;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.CustomerRepository;

import java.security.SecureRandom;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final SecureRandom random = new SecureRandom();

    private String generateAccountNumber(){
        long number = random.nextLong(1_000_000_000L, 10_000_000_000L);
        String accountNumber = Long.toString(number);

        while (accountRepository.existsByAccountNumber(accountNumber)){
            number = random.nextLong(1_000_000_000L, 10_000_000_000L);
            accountNumber = Long.toString(number);
        }

        return accountNumber;
    
    }

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(CreateAccountRequest request){
        System.out.println("Customer ID received: " + request.getCustomerId());
        System.out.println("Account type received: " + request.getAccountType());
        System.out.println("Initial deposit received: " + request.getInitialDeposit());
        Customer customer = customerRepository
        .findById(request.getCustomerId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        String newAccountNumber = generateAccountNumber();

        try {
        
            Account newAccount = new Account(
            newAccountNumber, 
            customer, 
            request.getAccountType(), 
            request.getInitialDeposit());

            return accountRepository.save(newAccount);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    public List<Account> findAccountsByCustomerId(Long customerId){
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return accountRepository.findByCustomerId(customerId);
    }

    public Account updateAccountStatus(String accountNumber, UpdateAccountStatusRequest request){
        Account account = findByAccountNumber(accountNumber);
        try {
            account.updateStatus(request.getAccountStatus());
            return accountRepository.save(account);
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

    public void closeAccount(String accountNumber){
        Account account = findByAccountNumber(accountNumber);

        try{
            account.updateStatus(AccountStatus.CLOSED);
            accountRepository.save(account);
        } catch (IllegalStateException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

}
