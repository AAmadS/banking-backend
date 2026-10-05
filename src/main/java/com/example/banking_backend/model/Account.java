package com.example.banking_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne 
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "balance")
    private BigDecimal balance;

    public Account (
        String accountNumber,
        Customer customer, 
        AccountType accountType, 
        BigDecimal balance){

        if (accountNumber == null || accountNumber.trim().isEmpty()){
            throw new IllegalArgumentException("Account Number cannot be null or empty");
        }

        if (customer == null ){
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (accountType == null){
            throw new IllegalArgumentException("Account Type cannot be null");
        }
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Balance cannot be less than 0 or null");
        }

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountType = accountType;
        this.accountStatus = AccountStatus.ACTIVE;
        this.balance = balance;

    }

    public Account (
        String accountNumber,
        Customer customer, 
        AccountType accountType){

        if (accountNumber == null || accountNumber.trim().isEmpty()){
            throw new IllegalArgumentException("Account Number cannot be null or empty");
        }
        if (customer == null){
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (accountType == null){
            throw new IllegalArgumentException("Account Type cannot be null");
        }

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountType = accountType;
        this.accountStatus = AccountStatus.ACTIVE;
        this.balance = BigDecimal.ZERO;
    }

    protected Account(){
    }

    public Long getId(){
        return id;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public Customer getCustomer(){
        return customer;
    }

    public AccountType getAccountType(){
        return accountType;
    }

    public AccountStatus getAccountStatus(){
        return accountStatus;
    }

    public BigDecimal getBalance(){
        return balance;
    }
    
}
