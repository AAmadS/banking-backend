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

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne 
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "balance", nullable = false)
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

        String normalizeAccountNumber = accountNumber.trim().replaceAll("\\s+", " ");

        this.accountNumber = normalizeAccountNumber;
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

    public void updateStatus(AccountStatus newStatus){
        if (newStatus == null){
            throw new IllegalArgumentException("Account Status cannot be null");
        }
        if (newStatus == AccountStatus.CLOSED && balance.compareTo(BigDecimal.ZERO) != 0){
            throw new IllegalStateException("Account Status cannot be CLOSE if balance is not ZERO");
        }

        this.accountStatus = newStatus;
    }

    public void deposit(BigDecimal deposit){
        if (accountStatus != AccountStatus.ACTIVE){
            throw new IllegalStateException("Cannot deposit into an account that is not ACTIVE");
        }
        if (deposit == null || deposit.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Deposit amount must be greater than ZERO");
        }

        balance = balance.add(deposit);
    }

    public void withdraw(BigDecimal withdraw){
        if (accountStatus != AccountStatus.ACTIVE){
            throw new IllegalStateException("Cannot withdraw into an account that is not ACTIVE");
        }
        if( withdraw == null || withdraw.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Withdraw amount must be greater than ZERO");
        }
        if (withdraw.compareTo(balance) > 0){
            throw new IllegalArgumentException("Withdraw cannot be greater than balance");
        }

        balance = balance.subtract(withdraw);
    }
    
}
