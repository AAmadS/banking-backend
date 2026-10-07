package com.example.banking_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;

public class TransactionResponse {
    private Long transactionId;
    private String accountNumber;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime occurredAt;
    private Long transferId;

    public TransactionResponse(Transaction transaction) {
        this.transactionId = transaction.getId();
        this.accountNumber = transaction.getAccount().getAccountNumber();
        this.transactionType = transaction.getTransactionType();
        this.amount = transaction.getAmount();
        this.occurredAt = transaction.getOccurredAt();

        if (transaction.getTransfer() != null) {
            this.transferId = transaction.getTransfer().getId();
        } else {
            this.transferId = null;
        }
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public Long getTransferId() {
        return transferId;
    }
}
