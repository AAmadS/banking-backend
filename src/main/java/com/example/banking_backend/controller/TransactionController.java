package com.example.banking_backend.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.TransactionResponse;
import com.example.banking_backend.dto.CreateTransactionRequest;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;
import com.example.banking_backend.service.TransactionService;

@RestController 
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionsService;

    public TransactionController(TransactionService transactionService){
        this.transactionsService = transactionService;
    }

    @PostMapping 
    public TransactionResponse createTransaction(@RequestBody CreateTransactionRequest request){
        Transaction transaction = transactionsService.createTransaction(request);

        return new TransactionResponse(transaction);
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransaction(@PathVariable Long id){
        Transaction transaction =  transactionsService.findById(id);

        return new TransactionResponse(transaction);
    }

    @GetMapping
    public List<TransactionResponse> getTransactions(
        @RequestParam(required = false) String accountNo,
        @RequestParam(required = false) TransactionType type,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime fromDate,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime toDate,

        @RequestParam(required = false) BigDecimal minAmount,
        @RequestParam(required = false) BigDecimal maxAmount){
            List<Transaction> transactions =  transactionsService.filterTransactions(
                accountNo,
                type, 
                fromDate, 
                toDate, 
                minAmount, 
                maxAmount);

                return transactions.stream().map(TransactionResponse::new).toList();
        }
        
}
