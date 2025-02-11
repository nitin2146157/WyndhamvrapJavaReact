package com.rci.wyndham.serviceimpl;

import com.rci.wyndham.dto.LeviesModel;
import com.rci.wyndham.entity.WMSPLeviesPayment;
import com.rci.wyndham.enums.PaymentStatusEnum;
import com.rci.wyndham.repository.WMSPLeviesPaymentRepository;
import com.rci.wyndham.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "leviesPaymentService")
public class WMSPLeviesPaymentServiceImpl implements PaymentService {

    @Autowired
    private WMSPLeviesPaymentRepository paymentRepository;

    @Override
    public WMSPLeviesPayment save(LeviesModel leviesModel) {
        WMSPLeviesPayment payment = new WMSPLeviesPayment();
        payment.setOwnerNumber(leviesModel.getOwnerNumber());
        payment.setAmount(leviesModel.getAmount());
        payment.setCreatedTs(new Date());
        payment.setPaymentStatus(PaymentStatusEnum.WAITING_PAYMENT.getValue());
        return paymentRepository.save(payment);
    }

    @Transactional
    @Override
    public WMSPLeviesPayment updatePayment(Integer sourceSystemPk, boolean isTransactionSuccessful) {
        WMSPLeviesPayment leviesPayment = paymentRepository.findById(sourceSystemPk).orElse(null);
        if (leviesPayment != null) {
            if (isTransactionSuccessful) {
                leviesPayment.setPaymentStatus(PaymentStatusEnum.PAYMENT_SUCCESSFUL.getValue());
            } else {
                leviesPayment.setPaymentStatus(PaymentStatusEnum.PAYMENT_UNSUCCESSFUL.getValue());
            }
            return paymentRepository.save(leviesPayment);
        }
        return null;
    }

    @Override
    public WMSPLeviesPayment getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public String getResponseDescription() {
        return "Levies";
    }
}