package com.rci.wyndham.entity;



import jakarta.persistence.*;
import org.springframework.util.StringUtils;


import java.util.Date;

/**
 * Defines the entity for a transaction.
 */
@Entity
@Table(name = "nab_direct_post_transactions")
public class NabDirectPostTransactions implements Cloneable {

    @Id
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
    private String id;

    @Column(name = "source_system")
    private String sourceSystem;

    @Column(name = "source_system_pk")
    private Integer sourceSystemPk;

    @Column(name = "merchant_id", nullable = false, columnDefinition = "char(16)")
    private String merchantId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency", columnDefinition = "char(7)")
    private String currency;

    @Column(name = "timestamp", columnDefinition = "char(14)")
    private String timeStamp;

    @Column(name = "fingerprint", nullable = false, columnDefinition = "char(64)")
    private String fingerprint;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "rescode", nullable = false, columnDefinition = "char(10)")
    private String resCode;

    @Column(name = "restext", nullable = false)
    private String resText;

    @Column(name = "txnid", nullable = false)
    private String transactionId;

    @Column(name = "settdate", nullable = false)
    private String settlementDate;

    @Column(name = "sig", nullable = false, columnDefinition = "char(40)")
    private String signature;

    @Column(name = "authid", nullable = false)
    private Integer authId;

    @Column(name = "preauthid", nullable = false, columnDefinition = "char(6)")
    private String preAuthId;

    @Column(name = "pan", nullable = false, columnDefinition = "char(12)")
    private String pan;

    @Column(name = "expirydate", nullable = false, columnDefinition = "char(6)")
    private String expiryDate;

    @Column(name = "created_ts")
    private Date createdTs;

    @Column(name = "modified_ts")
    private Date modifiedTs;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Transient
    public String getStatusMessage() {
        if (StringUtils.isEmpty(resCode)) {
            return null;
        }
        return resText;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Integer getSourceSystemPk() {
        return sourceSystemPk;
    }

    public void setSourceSystemPk(Integer sourceSystemPk) {
        this.sourceSystemPk = sourceSystemPk;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getFingerprint() {
            return fingerprint;
        }

        public void setFingerprint(String fingerprint) {
            this.fingerprint = fingerprint;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getResCode() {
            return resCode;
        }

        public void setResCode(String resCode) {
            this.resCode = resCode;
        }

        public String getResText() {
            return resText;
        }

        public void setResText(String resText) {
            this.resText = resText;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getSettlementDate() {
            return settlementDate;
        }

        public void setSettlementDate(String settlementDate) {
            this.settlementDate = settlementDate;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public Integer getAuthId() {
            return authId;
        }

        public void setAuthId(Integer authId) {
            this.authId = authId;
        }

        public String getPreAuthId() {
            return preAuthId;
        }

        public void setPreAuthId(String preAuthId) {
            this.preAuthId = preAuthId;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Date getCreatedTs() {
            return createdTs;
        }

        public void setCreatedTs(Date createdTs) {
            this.createdTs = createdTs;
        }

        public Date getModifiedTs() {
            return modifiedTs;
        }

        public void setModifiedTs(Date modifiedTs) {
            this.modifiedTs = modifiedTs;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
