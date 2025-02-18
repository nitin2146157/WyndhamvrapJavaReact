package com.rci.wyndham.entity;
import jakarta.persistence.*;

@Entity
@Table(name="payment_gateway_config")
public class PaymentGatewayConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_gateway_config_id", nullable = false)
    private Integer paymentGatewayConfigId;

    @Column(name = "post_url")
    private String postUrl;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "profile_id")
    private String profileId;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "payment_currency_id", referencedColumnName = "id", nullable = false)
    private PaymentCurrency paymentCurrency;

    @Column(name = "transaction_type")
    private String transactionType;

    // Getters and Setters
    public Integer getPaymentGatewayConfigId() {
        return paymentGatewayConfigId;
    }

    public void setPaymentGatewayConfigId(Integer paymentGatewayConfigId) {
        this.paymentGatewayConfigId = paymentGatewayConfigId;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentCurrency getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(PaymentCurrency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
