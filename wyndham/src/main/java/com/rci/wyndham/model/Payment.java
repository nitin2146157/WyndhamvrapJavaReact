package com.rci.wyndham.model;

import com.rci.wyndham.enums.PaymentSourceSystemEnum;

/**
 * Abstract class for Payment.
 */
public abstract class Payment {

    protected String currency;

    public boolean allowedToReceiveEmailReceipt() {
        return this.getEmailReceiptSentCount() < 3;
    }

    public void incrementReceiptSentCount() {
        this.setEmailReceiptSentCount(this.getEmailReceiptSentCount() + 1);
    }

    public abstract Integer getId();

    public abstract String getFirstName();

    public abstract String getLastName();

    public abstract String getEmail();

    public abstract Integer getEmailReceiptSentCount();

    public abstract void setEmailReceiptSentCount(Integer emailReceiptSentCount);

    public abstract double getAmount();

    public abstract String getReferenceNumber();

    public abstract PaymentSourceSystemEnum getSourceSystem();

    public abstract String getDescription();

    public abstract String getCurrency();
}