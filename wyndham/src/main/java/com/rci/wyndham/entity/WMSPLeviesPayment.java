package com.rci.wyndham.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wmsp_levies_payment")
public class WMSPLeviesPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "owner_number", nullable = false, columnDefinition = "char(20)")
    private String ownerNumber;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "created_ts", nullable = false)
    private Date createdTs;

    @Column(name = "payment_status", nullable = false, columnDefinition = "enum('waiting payment','payment successful','payment unsuccessful')")
    private String paymentStatus;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}