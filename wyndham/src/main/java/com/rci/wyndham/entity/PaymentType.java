package com.rci.wyndham.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity that represents a payment type.
 * @author fernando.alves.
 */
@Entity
@Table(name = "payment_type")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id", nullable = false, insertable = true, updatable = true)
    private Integer paymentTypeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "paymentType")
    private List<PaymentGatewayConfig> paymentGatewayConfigs;

    // Getters and setters
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PaymentGatewayConfig> getPaymentGatewayConfigs() {
        return paymentGatewayConfigs;
    }

    public void setPaymentGatewayConfigs(List<PaymentGatewayConfig> paymentGatewayConfigs) {
        this.paymentGatewayConfigs = paymentGatewayConfigs;
    }
}
