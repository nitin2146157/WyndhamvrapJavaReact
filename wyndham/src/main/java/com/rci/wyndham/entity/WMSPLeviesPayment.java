package com.rci.wyndham.entity;

import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.model.Payment;
import jakarta.persistence.*;
import java.util.Date;
/**
 * Defines the entity for the levies payment.
 */
@Entity
@Table(name="wmsp_levies_payment")
public class WMSPLeviesPayment extends Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "email_receipt_sent_count", nullable = false)
    private Integer emailReceiptSentCount;

    @Column(name = "owner_number", nullable = false, columnDefinition = "char(20)")
    private String ownerNumber;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "created_ts", nullable = false)
    private Date createdTs;

    @Column(name="payment_status", nullable = false, columnDefinition = "enum('waiting payment','payment successful','payment unsuccessful')")
    private String paymentStatus;

    @Column(name = "email_notification_sent_count")
    private Integer emailNotificationSentCount;

    @Column(name = "email_notification_error_count")
    private Integer emailNotificationErrorCount;

    @ManyToOne
    @JoinColumn(name = "payment_currency_id", referencedColumnName = "id", nullable = false)
    private PaymentCurrency paymentCurrency;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return fullName.substring(0, fullName.lastIndexOf(" ")+1).trim();
    }

    @Override
    public String getLastName() {
        return fullName.substring(fullName.lastIndexOf(" ")+1);
    }

    @Override
    public String getEmail() {
        return getEmailAddress();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getEmailReceiptSentCount() {
        return emailReceiptSentCount;
    }

    public void setEmailReceiptSentCount(Integer emailReceiptSentCount) {
        this.emailReceiptSentCount = emailReceiptSentCount;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public PaymentCurrency getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(PaymentCurrency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    @Override
    public String getReferenceNumber() {
        return ownerNumber;
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

    public Integer getEmailNotificationSentCount() {
        return emailNotificationSentCount;
    }

    public void setEmailNotificationSentCount(Integer emailNotificationSentCount) {
        this.emailNotificationSentCount = emailNotificationSentCount;
    }

    public Integer getEmailNotificationErrorCount() {
        return emailNotificationErrorCount;
    }

    public void setEmailNotificationErrorCount(Integer emailNotificationErrorCount) {
        this.emailNotificationErrorCount = emailNotificationErrorCount;
    }

    @Override
    public PaymentSourceSystemEnum getSourceSystem() {
        return PaymentSourceSystemEnum.WMSP_Levies;
    }

    @Transient
    @Override
    public String getDescription() {
        return "Levy Payment (" + this.getOwnerNumber() + ")";
    }

    @Transient
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Transient
    @Override
    public String getCurrency() {
        if(paymentCurrency != null) {
            return paymentCurrency.getName();
        }
        return null;
    }

    @Override
    public String toString() {
        return "WMSPLeviesPayment{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", ownerNumber='" + ownerNumber + '\'' +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}