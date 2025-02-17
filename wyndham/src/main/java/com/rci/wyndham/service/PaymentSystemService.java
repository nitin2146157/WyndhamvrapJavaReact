package com.rci.wyndham.service;

import com.rci.wyndham.entity.NabDirectPostTransactions;

public interface PaymentSystemService {
    boolean saveToken(NabDirectPostTransactions transaction, String paymentToken, String clientIp, String cardNumber,
                      String cardTypeValue, String expiryMm, String expiryYy);

    String getToken(String ownerNumber);
}
