package com.example.banking_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.CreateTransactionRequest;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.service.TransactionService;

@RestController 
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionsService;

    public TransactionController(TransactionService transactionService){
        this.transactionsService = transactionService;
    }

    @PostMapping 
    public Transaction createTransaction(@RequestBody CreateTransactionRequest request){
        return transactionsService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id){
        return transactionsService.findById(id);
    }
    

    
    
}
