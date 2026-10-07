package com.example.banking_backend.dto;

import java.math.BigDecimal;

import com.example.banking_backend.model.TransactionType;

public class CreateTransactionRequest {
    private String accountNumber;
    private TransactionType transactionType;
    private BigDecimal amount;

    public CreateTransactionRequest(){
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setTransactionType(TransactionType transactionType){
        this.transactionType = transactionType;
    }    

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}
