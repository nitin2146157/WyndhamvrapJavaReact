package com.rci.wyndham.enums;

public enum PaymentStatusEnum {

    WAITING_PAYMENT("waiting payment"),
    PAYMENT_SUCCESSFUL("payment successful"),
    PAYMENT_UNSUCCESSFUL("payment unsuccessful");

    private String value;

    PaymentStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
