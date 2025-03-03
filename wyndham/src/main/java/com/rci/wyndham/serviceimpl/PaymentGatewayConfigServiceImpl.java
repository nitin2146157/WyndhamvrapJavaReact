package com.rci.wyndham.serviceimpl;

import com.google.common.collect.Lists;
import com.rci.wyndham.entity.CardType;
import com.rci.wyndham.entity.PaymentCurrency;
import com.rci.wyndham.entity.PaymentGatewayConfig;
import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.repository.CardTypeRepository;
import com.rci.wyndham.repository.PaymentCurrencyRepository;
import com.rci.wyndham.repository.PaymentGatewayConfigRepository;
import com.rci.wyndham.repository.PaymentTypeRepository;
import com.rci.wyndham.service.PaymentGatewayConfigService;
import com.wyn.sales.dto.PaymentType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentGatewayConfigServiceImpl extends BaseObject implements PaymentGatewayConfigService {
    @Autowired
    private PaymentGatewayConfigRepository paymentGatewayConfigRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private PaymentCurrencyRepository paymentCurrencyRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public PaymentGatewayConfig getPaymentGatewayConfig(PaymentSourceSystemEnum sourceSystem, String currency) {
        LOGGER.info("getting payment gateway configuration for " + sourceSystem);
        PaymentType paymentType = paymentTypeRepository.findByName(sourceSystem.getName());
        PaymentCurrency paymentCurrency = paymentCurrencyRepository.findByName(currency);
        return paymentGatewayConfigRepository.findByPaymentTypeAndPaymentCurrency(paymentType, paymentCurrency);
    }

    @Override
    public PaymentGatewayConfig getPaymentGatewayConfigWithMidAndCurrency(PaymentSourceSystemEnum sourceSystem, String merchantId, String currency) {
        LOGGER.info("getting payment gateway configuration for " + sourceSystem);
        LOGGER.info("source system: " + sourceSystem.getName());
        LOGGER.info("currency: " + currency);
        LOGGER.info("merchant Id: " + merchantId);

        PaymentType paymentType = paymentTypeRepository.findByName(sourceSystem.getName());
        PaymentCurrency paymentCurrency = paymentCurrencyRepository.findByName(currency);
        return paymentGatewayConfigRepository.findFirstByPaymentTypeAndMerchantIdAndPaymentCurrency(paymentType, merchantId, paymentCurrency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CardType> getCardTypes() {
        Iterable<CardType> cardTypes = cardTypeRepository.findAll();
        return Lists.newArrayList(cardTypes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentType> getPaymentTypes() {
        Iterable<PaymentType> payments = paymentTypeRepository.findAll();
        return Lists.newArrayList(payments);
    }

    @Override
    public PaymentGatewayConfig getPaymentGatewayConfigWithMidAndTransactionType(PaymentSourceSystemEnum sourceSystem, String merchantId, String transactionType) {
        LOGGER.info("getting payment gateway configuration for sourceSystem: " + sourceSystem + " :: merchantId: " + merchantId);
        PaymentType paymentType = paymentTypeRepository.findByName(sourceSystem.getName());
        return paymentGatewayConfigRepository.findFirstByPaymentTypeAndMerchantIdAndTransactionType(paymentType, merchantId, transactionType);
    }

    @Override
    public PaymentGatewayConfig getPaymentGatewayConfigWithTransactionType(PaymentSourceSystemEnum sourceSystem, String transactionType) {
        LOGGER.info("getting payment gateway configuration for sourceSystem: " + sourceSystem + " :: transactionType: " + transactionType);
        PaymentType paymentType = paymentTypeRepository.findByName(sourceSystem.getName());
        return paymentGatewayConfigRepository.findFirstByPaymentTypeAndTransactionType(paymentType, transactionType);
    }
}
