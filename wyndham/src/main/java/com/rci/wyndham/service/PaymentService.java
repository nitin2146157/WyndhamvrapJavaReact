package com.rci.wyndham.service;

import com.rci.wyndham.dto.LeviesModel;
import com.rci.wyndham.entity.WMSPLeviesPayment;
import com.rci.wyndham.model.Payment;


/**
 * PaymentService interface
 */
public interface PaymentService {

    Payment save(Payment payment);

    Payment updatePayment(Integer sourceSystemPk, boolean isTransactionSuccessful);

    Payment getPaymentById(Integer paymentId);

    String getResponseDescription();
}