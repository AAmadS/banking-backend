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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
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

    public void updateContactInfo(String email, String phone) {
        handleIllegalArgumentString(email, "Email cannot be null or empty");
        handleIllegalArgumentString(phone, "Phone cannot be null or empty");

        this.email = normalizeString(email);
        this.phone = normalizeString(phone);
    }

}
