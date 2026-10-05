package com.example.banking_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "account_id", nullable =  false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @ManyToOne
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;

    public Transaction(
        Account account,
        TransactionType transactionType,
        BigDecimal amount,
        Transfer transfer
    ){
        if(account == null){
            throw new IllegalArgumentException("Account cannot be null");
        }

        if(transactionType == null){
            throw new IllegalArgumentException("Transaction Type cannot be null");

        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount cannot be 0 or less than 0 or null");
        }

        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.occurredAt = LocalDateTime.now();
        this.transfer = transfer;

    }

    public Transaction(
        Account account,
        TransactionType transactionType,
        BigDecimal amount
    ){
        if(account == null){
            throw new IllegalArgumentException("Account cannot be null");
        }

        if(transactionType == null){
            throw new IllegalArgumentException("Transaction Type cannot be null");

        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be greater than 0 and not null");
        }

        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.occurredAt = LocalDateTime.now();
        this.transfer = null;

    }

    protected Transaction(){
    }

    public Long getId(){
        return id;
    }

    public Account getAccount(){
        return account;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public LocalDateTime getOccurredAt(){
        return occurredAt;
    }

    public Transfer getTransfer(){
        return transfer;
    }

}
