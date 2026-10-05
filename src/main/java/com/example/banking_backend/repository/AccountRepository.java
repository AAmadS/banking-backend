package com.example.banking_backend.repository;

import com.example.banking_backend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
