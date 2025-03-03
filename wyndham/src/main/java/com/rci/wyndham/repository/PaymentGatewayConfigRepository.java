package com.rci.wyndham.repository;

import com.rci.wyndham.entity.PaymentCurrency;
import com.rci.wyndham.entity.PaymentGatewayConfig;
import com.rci.wyndham.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGatewayConfigRepository extends JpaRepository<PaymentGatewayConfig,Integer> {


    PaymentGatewayConfig findByPaymentTypeAndPaymentCurrency(PaymentType paymentType, PaymentCurrency currency);

    PaymentGatewayConfig findFirstByPaymentTypeAndMerchantIdAndPaymentCurrency(PaymentType paymentType,
                                                                               String merchantId,PaymentCurrency currency);


    PaymentGatewayConfig findFirstByPaymentTypeAndMerchantIdAndTransactionType(PaymentType paymentType,
                                                                               String merchantId, String transactionType);

    PaymentGatewayConfig findFirstByPaymentTypeAndTransactionType(PaymentType paymentType, String transactionType);
}

