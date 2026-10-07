package com.example.banking_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.AccountResponse;
import com.example.banking_backend.dto.TransactionResponse;
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
    public AccountResponse createAccount(@RequestBody CreateAccountRequest request){
        Account account = accountService.createAccount(request);
        return new AccountResponse(account);
    }

    @GetMapping("/{acctNo}")
    public AccountResponse getAccount(@PathVariable String acctNo) {
        Account account = accountService.findByAccountNumber(acctNo);

        return new AccountResponse(account);
    }

    @PutMapping("/{acctNo}")
    public AccountResponse updateAccount(@PathVariable String acctNo, @RequestBody UpdateAccountStatusRequest request) {
        Account account = accountService.updateAccountStatus(acctNo, request);

        return new AccountResponse(account);
    }

    @DeleteMapping("/{acctNo}")
    public void deleteAccount(@PathVariable String acctNo) {
        accountService.closeAccount(acctNo);
    }

    @GetMapping("/{acctNo}/transactions")
    public List<TransactionResponse> getAccountTransactions(@PathVariable String acctNo) {

        List<Transaction> transactions = transactionService.findByAccountNumber(acctNo);

        return transactions.stream().map(TransactionResponse::new).toList();
    }
}
