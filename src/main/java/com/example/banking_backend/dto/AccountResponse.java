package com.example.banking_backend.dto;

import java.math.BigDecimal;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.model.AccountType;

public class AccountResponse {

    private Long accountId;
    private String accountNumber;
    private Long customerId;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private BigDecimal balance;

    public AccountResponse(Account account) {
        this.accountId = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.customerId = account.getCustomer().getId();
        this.accountType = account.getAccountType();
        this.accountStatus = account.getAccountStatus();
        this.balance = account.getBalance();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
