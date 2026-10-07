package com.example.banking_backend.dto;

import com.example.banking_backend.model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferResponse {
    private Long transferId;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal amount;
    private LocalDateTime occurredAt;

    public TransferResponse(Transfer transfer) {
        this.transferId = transfer.getId();
        this.sourceAccountNumber = transfer.getSourceAccount().getAccountNumber();
        this.targetAccountNumber = transfer.getTargetAccount().getAccountNumber();
        this.amount = transfer.getAmount();
        this.occurredAt = transfer.getOccurredAt();
    }

    public Long getTransferId() {
        return transferId;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

}
