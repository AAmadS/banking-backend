package com.example.banking_backend.repository;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    boolean existsByCustomerIdAndAccountStatusNot(Long customerId, AccountStatus accountStatus);
}
