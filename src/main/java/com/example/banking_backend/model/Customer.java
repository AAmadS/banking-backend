package com.example.banking_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    private void handleIllegalArgumentString(String value, String text) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(text);
        }
    }

    private String normalizeString(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    public Customer(
            String firstName,
            String lastName,
            String email,
            String phone) {

        handleIllegalArgumentString(firstName, "First name cannot be empty or null");
        handleIllegalArgumentString(lastName, "Last name cannot be empty or null");
        handleIllegalArgumentString(email, "Email name cannot be empty or null");
        handleIllegalArgumentString(phone, "Phone number cannot be empty or null");

        this.firstName = normalizeString(firstName);
        this.lastName = normalizeString(lastName);
        this.email = normalizeString(email);
        this.phone = normalizeString(phone);

    }
    
    protected Customer(){
    }

    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phone;
    }

}
