package com.rci.wyndham.dto;

import org.springframework.lang.NonNull;

import jakarta.annotation.Nonnull;

public class LeviesModel {

    @Nonnull
    private String ownerNumber;
    
    @Nonnull
    private String fullName;

    @Nonnull
    private double amount;

    // Getters and Setters

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
