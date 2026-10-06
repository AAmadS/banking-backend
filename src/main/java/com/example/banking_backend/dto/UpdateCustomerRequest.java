package com.example.banking_backend.dto;

public class UpdateCustomerRequest {
    private String email;
    private String phone;

    public UpdateCustomerRequest(){

    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
