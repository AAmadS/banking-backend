package com.example.banking_backend.dto;

import com.example.banking_backend.model.AccountStatus;

public class UpdateAccountStatusRequest {
    private AccountStatus accountStatus;

    public UpdateAccountStatusRequest(){
    }

    public AccountStatus getAccountStatus(){
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus){
        this.accountStatus = accountStatus;
    }

}

