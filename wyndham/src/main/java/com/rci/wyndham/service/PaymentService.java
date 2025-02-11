package com.rci.wyndham.service;

import com.rci.wyndham.dto.LeviesModel;
import com.rci.wyndham.entity.WMSPLeviesPayment;

public interface PaymentService {
    WMSPLeviesPayment save(LeviesModel leviesModel);
    WMSPLeviesPayment updatePayment(Integer sourceSystemPk, boolean isTransactionSuccessful);
    WMSPLeviesPayment getPaymentById(Integer paymentId);
    String getResponseDescription();
}