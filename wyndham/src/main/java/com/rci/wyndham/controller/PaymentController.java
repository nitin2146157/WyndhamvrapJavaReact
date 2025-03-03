package com.rci.wyndham.controller;

import com.rci.wyndham.dto.LeviesModel;
import com.rci.wyndham.entity.PaymentGatewayConfig;
import com.rci.wyndham.entity.WMSPLeviesPayment;
import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.enums.PaymentStatusEnum;
import com.rci.wyndham.repository.PaymentCurrencyRepository;
import com.rci.wyndham.service.PaymentGatewayConfigService;
import com.rci.wyndham.service.PaymentService;
import com.rci.wyndham.service.TransactionService;
import com.rci.wyndham.serviceimpl.CyberSourceFormDataFactory;
import com.rci.wyndham.util.CreditCardValidatorUtil;
import com.rci.wyndham.util.SecurityHelper;
import com.wyn.util.WynGeneralUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static com.rci.wyndham.model.BaseObject.LOGGER;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PaymentGatewayConfigService paymentGatewayConfigService;

    @Autowired
    private PaymentCurrencyRepository paymentCurrencyRepository;

    @Autowired
    private CyberSourceFormDataFactory cyberSourceFormDataFactory;


    @GetMapping("/aud-levies")
    //@RequestMapping(value = , method = RequestMethod.GET)
    public ResponseEntity<LeviesModel> audLevies() {
        LOGGER.info("loading levies page");
        //   ModelAndView mav = new ModelAndView("payment/aud-levies");
        LeviesModel levies = new LeviesModel();
        String loggedOwnerNumber = securityHelper.getLoggedUser();
        levies.setOwnerNumber(loggedOwnerNumber);
        // mav.addObject("leviesModel", levies);
        //   return mav;
        return ResponseEntity.ok(levies);
    }

    @RequestMapping(value = "/aud-levies", method = RequestMethod.POST)
    public ModelAndView processAudLevies(@RequestBody LeviesModel levies) {

        LOGGER.info("Levies payment submitted...");

        //   ModelAndView mav = new ModelAndView("payment/cyberSource/verifyPayment");

//        if(levies.getOwnerNumber().contains("1101901047") || levies.getOwnerNumber().contains("2022201376"))  {
//            LOGGER.info("We cannot process payments at this time");
//            result.reject("We cannot process payments at this time");
//        }
//
//        if (result.hasErrors()) {
//            LOGGER.info("Invalid form");
//            mav = new ModelAndView("payment/aud-levies");
//            mav.addObject("errorMessage", "We cannot process payments at this time");
//            mav.addObject("leviesModel", levies);
//            return mav;
//        }


        LOGGER.info("Valid form, processing...");

        String ownerNumber = levies.getOwnerNumber();

        //create order and redirect to credit card payment form.
        String uniqid = WynGeneralUtil.uniqid("", false);

        WMSPLeviesPayment payment = new WMSPLeviesPayment();
        payment.setFullName(StringUtils.trim(levies.getFullName()));
        payment.setEmailAddress(levies.getEmail());
        payment.setEmailReceiptSentCount(0);
        payment.setOwnerNumber(ownerNumber);
        payment.setAmount(levies.getAmount());
        payment.setPaymentCurrency(paymentCurrencyRepository.findByName(levies.getCurrency()));
        payment.setCurrency(levies.getCurrency());
        payment.setCreatedTs(new Date());
        payment.setPaymentStatus(PaymentStatusEnum.WAITING_PAYMENT.getValue());

        String fingerPrint = cyberSourceFormDataFactory.build(uniqid, payment, null);

        PaymentGatewayConfig paymentGatewayConfig = paymentGatewayConfigService.getPaymentGatewayConfig(PaymentSourceSystemEnum.WMSP_Levies, payment.getCurrency());

        transactionService.save(uniqid, fingerPrint, payment, paymentGatewayConfig.getMerchantId());

        mav.addObject("ccValidatorUtil", new CreditCardValidatorUtil());
        mav.addObject("ccFormTitle", getCCFormTitle(PaymentSourceSystemEnum.WMSP_Levies));

        return mav;
    }

}