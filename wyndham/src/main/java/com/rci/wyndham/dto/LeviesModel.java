package com.rci.wyndham.dto;

import jakarta.*;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * DTO class for LeviesModel.
 */
public class LeviesModel {

    @NotNull
    private String ownerNumber;

    private String fullName;

    @NotNull
    private double amount;

    @NotNull
    private String currency;

    private String email;

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
