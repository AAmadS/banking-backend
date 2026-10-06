package com.example.banking_backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.CreateAccountRequest;
import com.example.banking_backend.dto.UpdateAccountStatusRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.service.AccountService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody CreateAccountRequest request){
        return accountService.createAccount(request);
    }

    @GetMapping("/{acctNo}")
    public Account getAccount(@PathVariable String acctNo){
        return accountService.findByAccountNumber(acctNo);
    }

    @PutMapping("/{acctNo}")
    public Account updateAccount(@PathVariable String acctNo, @RequestBody UpdateAccountStatusRequest request){
        return accountService.updateAccountStatus(acctNo, request);
    }

    @DeleteMapping("/{acctNo}")
    public void deleteAccount(@PathVariable String acctNo){
        accountService.closeAccount(acctNo);
    }
    
}
