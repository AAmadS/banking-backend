package com.example.banking_backend.controller;

import java.util.List;

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
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.service.AccountService;
import com.example.banking_backend.service.TransactionService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(
            AccountService accountService,
            TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public Account createAccount(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/{acctNo}")
    public Account getAccount(@PathVariable String acctNo) {
        return accountService.findByAccountNumber(acctNo);
    }

    @PutMapping("/{acctNo}")
    public Account updateAccount(@PathVariable String acctNo, @RequestBody UpdateAccountStatusRequest request) {
        return accountService.updateAccountStatus(acctNo, request);
    }

    @DeleteMapping("/{acctNo}")
    public void deleteAccount(@PathVariable String acctNo) {
        accountService.closeAccount(acctNo);
    }

    @GetMapping("/{acctNo}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable String acctNo) {
        return transactionService.findByAccountNumber(acctNo);
    }
}
