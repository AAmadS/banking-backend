package com.example.banking_backend.dto;

import java.math.BigDecimal;

import com.example.banking_backend.model.AccountType;

public class CreateAccountRequest {
    private Long customerId;
    private AccountType accountType;
    private BigDecimal initialDeposit;

    public CreateAccountRequest(){
    }

    public Long getCustomerId(){
        return customerId;
    }

    public AccountType getAccountType(){
        return accountType;
    }

    public BigDecimal getInitialDeposit(){
        return initialDeposit;
    }

    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    public void setInitialDeposit(BigDecimal initialDeposit){
        this.initialDeposit = initialDeposit;
    }
    
}
