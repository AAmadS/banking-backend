package com.example.banking_backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_backend.dto.CreateTransferRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;
import com.example.banking_backend.model.Transfer;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.TransactionRepository;
import com.example.banking_backend.repository.TransferRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransferService(
            TransferRepository transferRepository,
            TransactionRepository transactionRepository,
            AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transfer createTransfer(CreateTransferRequest request) {
        Account sourceAccount = accountRepository
                .findByAccountNumber(request.getSourceAccountNumber())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Source account not found"));

        Account targetAccount = accountRepository
                .findByAccountNumber(request.getTargetAccountNumber())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Target account not found"));

        if (sourceAccount.getAccountNumber().equals(targetAccount.getAccountNumber())) {
            throw new IllegalArgumentException("Source and Target accounts cannot be the same");
        }

        sourceAccount.withdraw(request.getTransferAmount());
        targetAccount.deposit(request.getTransferAmount());

        Transfer newTransfer = new Transfer(
                sourceAccount,
                targetAccount,
                request.getTransferAmount());

        newTransfer = transferRepository.save(newTransfer);

        Transaction withdrawalTransaction = new Transaction(
                sourceAccount,
                TransactionType.WITHDRAWAL,
                request.getTransferAmount(),
                newTransfer);

        Transaction depositTransaction = new Transaction(
                targetAccount,
                TransactionType.DEPOSIT,
                request.getTransferAmount(),
                newTransfer);

        transactionRepository.save(withdrawalTransaction);
        transactionRepository.save(depositTransaction);

        return newTransfer;
    }
}
