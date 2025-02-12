package com.rci.wyndham.service;

import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.model.BaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory extends BaseObject {

    @Autowired
    @Qualifier("leviesPaymentService")
    private PaymentService leviesPaymentService;

    @Autowired
    @Qualifier("financePaymentService")
    private PaymentService financePaymentService;

    @Autowired
    @Qualifier("depositsPaymentService")
    private PaymentService depositsPaymentService;

    @Autowired
    @Qualifier("discoveryPaymentService")
    private PaymentService discoveryPaymentService;

    @Autowired
    @Qualifier("minivacsPaymentService")
    private PaymentService minivacsPaymentService;

    @Autowired
    @Qualifier("paylinkPaymentService")
    private PaymentService paylinkPaymentService;

    @Autowired

    @Qualifier("expressCheckinPaymentService")

    private PaymentService expressCheckinPaymentService;

    public PaymentService getInstance(PaymentSourceSystemEnum sourceSystemEnum) {
        if(PaymentSourceSystemEnum.WMSP_Levies.equals(sourceSystemEnum)) {
            return leviesPaymentService;
        }
        else if(PaymentSourceSystemEnum.WMSP_Finance.equals(sourceSystemEnum)) {
            return financePaymentService;
        }
        else if(PaymentSourceSystemEnum.WMSP_Deposits.equals(sourceSystemEnum)) {
            return depositsPaymentService;
        }
        else if(PaymentSourceSystemEnum.WMSP_Discovery.equals(sourceSystemEnum)) {
            return discoveryPaymentService;
        }
        else if(PaymentSourceSystemEnum.WMSP_Minivacs.equals(sourceSystemEnum)) {
            return minivacsPaymentService;
        } else if(PaymentSourceSystemEnum.PAYLINK.equals(sourceSystemEnum)) {
            return paylinkPaymentService;
        }
        else if(PaymentSourceSystemEnum.EXPRESS_CHECKIN.equals(sourceSystemEnum)) {
            return expressCheckinPaymentService;
        }
        return null;
    }

}
