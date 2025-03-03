package com.rci.wyndham.service;

import com.rci.wyndham.entity.CardType;
import com.rci.wyndham.entity.PaymentGatewayConfig;
import com.rci.wyndham.entity.PaymentType;
import com.rci.wyndham.enums.PaymentSourceSystemEnum;

import java.util.List;

public interface PaymentGatewayConfigService {

    /**
     * Gets the payment gateway configuration for the given payment source.
     * @param sourceSystem the payment source
     * @param currency the currency
     * @return the payment gateway configuration
     */
    PaymentGatewayConfig getPaymentGatewayConfig(PaymentSourceSystemEnum sourceSystem, String currency);


    PaymentGatewayConfig getPaymentGatewayConfigWithMidAndCurrency(PaymentSourceSystemEnum sourceSystem, String merchantId,String currency);

    PaymentGatewayConfig getPaymentGatewayConfigWithMidAndTransactionType(PaymentSourceSystemEnum sourceSystem, String merchantId, String transactionType);

    PaymentGatewayConfig getPaymentGatewayConfigWithTransactionType(PaymentSourceSystemEnum sourceSystem, String transactionType);

    /**
     * Gets the list of all card types.
     * @return the list of card types
     */
    List<CardType> getCardTypes();

    /**
     * Gets the list of all payment types.
     * @return the list of payment types
     */
    List<PaymentType> getPaymentTypes();

}
