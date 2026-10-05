package com.example.banking_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banking_backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
