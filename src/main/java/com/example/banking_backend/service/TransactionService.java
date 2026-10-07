package com.example.banking_backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_backend.dto.CreateTransactionRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(CreateTransactionRequest request) {

        try {
            Account account = accountRepository
                    .findByAccountNumber(request.getAccountNumber())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

            if (request.getTransactionType() == TransactionType.DEPOSIT) {
                account.deposit(request.getAmount());
            } else if (request.getTransactionType() == TransactionType.WITHDRAWAL) {
                account.withdraw(request.getAmount());
            }

            Transaction newTransaction = new Transaction(
                    account,
                    request.getTransactionType(),
                    request.getAmount());

            return transactionRepository.save(newTransaction);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Transaction findById(Long id) {
        return transactionRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    public List<Transaction> findByAccountNumber(String accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account not found"));

        return transactionRepository.findByAccountId(account.getId());
    }

    public List<Transaction> filterTransactions(
        String accountNo,
        TransactionType type,
        LocalDateTime fromDate,
        LocalDateTime toDate,
        BigDecimal minAmount,
        BigDecimal maxAmount
    ){
        return transactionRepository.filterTransactions(
            type,
            accountNo,
            fromDate,
            toDate,
            minAmount,
            maxAmount
        );
    }
}
