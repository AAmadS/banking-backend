package com.example.banking_backend.repository;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    boolean existsByCustomerIdAndAccountStatusNot(Long customerId, AccountStatus accountStatus);

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Long customerId);
}
