package com.rci.wyndham.enums;

public enum PaymentSourceSystemEnum {

    WMSP_Finance("WMSP-Finance"),
    WMSP_Discovery("WMSP-Discovery"),
    WMSP_Levies("WMSP-Levies"),
    Shop_Membership("Shop-Membership"),
    Shop_Invoice("Shop-Invoice"),
    HBW_Package("HBW-Package"),
    PBW("PBW"),
    WMSP_Deposits("WMSP-Deposits"),
    WMSP_Minivacs("WMSP-Minivacs"),

    PAYLINK("Paylink"),

    PAYLINK_CONSUMER_FINANCE_FINANCE("Paylink-Consumer-Finance-Finance"),
    PAYLINK_CONSUMER_FINANCE_DISCOVERY("Paylink-Consumer-Finance-Discovery"),
    PAYLINK_CONSUMER_FINANCE_TRANSFER("Paylink-Consumer-Finance-Transfer"),
    PAYLINK_CONSUMER_FINANCE_DEPOSIT("Paylink-Consumer-Finance-Deposit"),
    PAYLINK_CONSUMER_FINANCE_LEVIES("Paylink-Consumer-Finance-Levies"),
    PAYLINK_OWNER_SERVICES("Paylink-Owner-Services"),
    PAYLINK_MARKETING("Paylink-Marketing"),
    EXPRESS_CHECKIN("Express-Checkin");

    private String name;

    PaymentSourceSystemEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PaymentSourceSystemEnum loadByName(String name) {
        PaymentSourceSystemEnum[] values = values();
        for (PaymentSourceSystemEnum value : values) {
            if(value.getName().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("could not find PaymentSourceSystemEnum name " + name);
    }

}