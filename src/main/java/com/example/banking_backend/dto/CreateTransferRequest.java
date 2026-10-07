package com.example.banking_backend.dto;

import java.math.BigDecimal;

public class CreateTransferRequest {
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal transferAmount;

    public CreateTransferRequest(){
    }

    public String getSourceAccountNumber(){
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber(){
        return targetAccountNumber;
    }

    public BigDecimal getTransferAmount(){
        return transferAmount;
    }

    public void setSourceAccountNumber(String sourceAccountNumber){
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber){
        this.targetAccountNumber = targetAccountNumber;
    }

    public void setTransferAmount(BigDecimal transferAmount){
        this.transferAmount = transferAmount;
    }

}