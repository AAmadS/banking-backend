package com.example.banking_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_acc_id", nullable = false)
    private Account sourceAcc;

    @ManyToOne
    @JoinColumn(name = "target_acc_id", nullable = false)
    private Account targetAcc;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    public Transfer(
            Account sourceAcc,
            Account targetAcc,
            BigDecimal amount) {
        if (sourceAcc == null || targetAcc == null) {
            throw new IllegalArgumentException("Source and Target account cannot be null");
        }
        if(sourceAcc.getAccountNumber().equals(targetAcc.getAccountNumber())){
            throw new IllegalArgumentException("Source and Target cannot be the same number");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0 and not null");
        }

        this.sourceAcc = sourceAcc;
        this.targetAcc = targetAcc;
        this.amount = amount;
        this.occurredAt = LocalDateTime.now();

    }

    protected Transfer(){
    }

    public Long getId(){
        return id;
    }

    public Account getSourceAccount(){
        return sourceAcc;
    }

    public Account getTargetAccount(){
        return targetAcc;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public LocalDateTime getOccurredAt(){
        return occurredAt;
    }
}
