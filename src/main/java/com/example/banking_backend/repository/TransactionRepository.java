package com.example.banking_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
    List<Transaction> findByAccountId(Long accountId);

    @Query("""
            SELECT t FROM Transaction t
            WHERE (:type IS NULL OR t.transactionType = :type)
            AND (:accountNo IS NULL OR t.account.accountNumber = :accountNo)
            AND (CAST(:fromDate AS timestamp) IS NULL OR t.occurredAt >= :fromDate)
            AND (CAST(:toDate AS timestamp) IS NULL OR t.occurredAt <= :toDate)
            AND (:minAmount IS NULL OR t.amount >= :minAmount)
            AND (:maxAmount IS NULL OR t.amount <= :maxAmount)
            """)
    List<Transaction> filterTransactions(
        @Param("type") TransactionType type,
        @Param("accountNo") String accountNo,
        @Param("fromDate") LocalDateTime fromDate,
        @Param("toDate") LocalDateTime toDate,
        @Param("minAmount") BigDecimal minAmount,
        @Param("maxAmount") BigDecimal maxAmount);
}
