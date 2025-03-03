package com.rci.wyndham.serviceimpl;

import com.google.api.client.util.DateTime;
import com.rci.wyndham.entity.PaymentGatewayConfig;
import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.model.Payment;
import com.rci.wyndham.service.PaymentGateWayFormDataFactory;
import com.rci.wyndham.service.PaymentGatewayConfigService;
import com.rci.wyndham.util.WVRAPUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Service
public class CyberSourceFormDataFactory extends BaseObject implements PaymentGateWayFormDataFactory {

    @Value("${cybersource.sop.signed.field.names}")
    private String signedSOPFieldNames;

    @Value("${cybersource.sop.unsigned.field.names}")
    private String unsignedSOPFieldNames;

    @Value("${cybersource.wm.signed.field.names}")
    private String signedWMFieldNames;

    @Value("${cybersource.wm.unsigned.field.names}")
    private String unsignedWMFieldNames;

    @Value("${cybersource.wm.token.signed.field.names}")
    private String signedWMTokenFieldNames;

    @Value("${cybersource.wm.token.unsigned.field.names}")
    private String unsignedWMTokenFieldNames;

    @Value("${cybersource.default.payment.method}")
    private String defaultPaymentMethod;

    @Value("${cybersource.default.currency}")
    private String defaultCurrency;

    @Value("${cybersource.default.transaction.type}")
    private String defaultTransactionType;

    @Value("${cybersource.default.locale}")
    private String defaultLocale;

    @Value("${cybersource.signed.date.format}")
    private String defaultSignedDateFormat;

    @Value("${cybersource.signed.date.time.zone}")
    private String defaultSignedDateTimeZone;

    @Autowired
    private PaymentGatewayConfigService paymentGatewayConfigService;

    public String build(String uniqueId, Payment payment, ModelAndView mav) {
        LOGGER.info("building parameters for unique id >> " + uniqueId);

        PaymentSourceSystemEnum sourceSystem = payment.getSourceSystem();
        PaymentGatewayConfig paymentGatewayConfig = paymentGatewayConfigService.getPaymentGatewayConfig(sourceSystem, payment.getCurrency());
        LOGGER.info("is it null " + (paymentGatewayConfig == null ? "true" : "false"));

        if (paymentGatewayConfig != null) {
            mav.addObject("postActionUrl", paymentGatewayConfig.getPostUrl());
            LOGGER.info("merchantId before form data" + paymentGatewayConfig.getMerchantId());
            Map<String, String> formData = getFormData(uniqueId, payment, paymentGatewayConfig);
            String fingerprint = generateFingerprint(formData, signedWMFieldNames, paymentGatewayConfig.getSecretKey());
            mav.addObject("signature", fingerprint);
            mav.addObject("expiryYearList", getExpiryYearList());
            mav.addAllObjects(formData);

            return fingerprint;
        }

        return null;
    }

    public String buildToken(String uniqueId, PaymentTokenModel model, ModelAndView mav, String transactionType,
                             PaymentGatewayConfig paymentGatewayConfig) {
        LOGGER.info("building parameters for unique id >> " + uniqueId);

        if (paymentGatewayConfig != null) {
            mav.addObject("postActionUrl", paymentGatewayConfig.getPostUrl());
            Map<String, String> formData = getFormDataTransactionType(uniqueId, model, paymentGatewayConfig,
                    transactionType, signedWMFieldNames);
            String fingerprint = generateFingerprint(formData, signedWMFieldNames, paymentGatewayConfig.getSecretKey());
            mav.addObject("signature", fingerprint);
            mav.addAllObjects(formData);

            return fingerprint;
        }

        return null;
    }

    public String buildWithPayToken(String uniqueId, PaymentTokenModel model, ModelAndView mav, String transactionType,
                                    PaymentGatewayConfig paymentGatewayConfig) {
        LOGGER.info("building parameters for unique id >> " + uniqueId);

        if (paymentGatewayConfig != null) {
            mav.addObject("postActionUrl", paymentGatewayConfig.getPostUrl());
            Map<String, String> formData = getFormDataTransactionType(uniqueId, model, paymentGatewayConfig,
                    transactionType, signedWMTokenFieldNames);
            String fingerprint = generateFingerprint(formData, signedWMTokenFieldNames, paymentGatewayConfig.getSecretKey());
            mav.addObject("signature", fingerprint);
            mav.addAllObjects(formData);

            return fingerprint;
        }

        return null;
    }

    public String build(String uniqueId, Payment payment, ModelAndView mav, String merchantId, PaymentSourceSystemEnum sourceSystem) {
        LOGGER.info("building parameters for unique id >> " + uniqueId);

        PaymentGatewayConfig paymentGatewayConfig = paymentGatewayConfigService.getPaymentGatewayConfigWithMidAndCurrency(sourceSystem, merchantId, payment.getCurrency());

        if (paymentGatewayConfig != null) {
            String signedFieldNames = paymentGatewayConfig.getPostUrl().contains("silent") ? signedSOPFieldNames : signedWMFieldNames;

            mav.addObject("postActionUrl", paymentGatewayConfig.getPostUrl());
            LOGGER.info("merchantId before form data" + paymentGatewayConfig.getMerchantId());
            Map<String, String> formData = getFormDataWithMerchantId(uniqueId, payment, paymentGatewayConfig);
            String fingerprint = generateFingerprint(formData, signedFieldNames, paymentGatewayConfig.getSecretKey());
            mav.addObject("signature", fingerprint);
            mav.addObject("expiryYearList", getExpiryYearList());
            mav.addAllObjects(formData);

            return fingerprint;
        }

        return null;
    }

    private Map<String, String> getFormDataWithMerchantId(String uniqueId, Payment payment, PaymentGatewayConfig paymentGatewayConfig) {
        Map<String, String> data = new HashMap<>();

        LOGGER.info("getting form data for unique id >> " + uniqueId);

        String signedDateTime = WVRAPUtil.formatDate(new Date(), defaultSignedDateFormat, defaultSignedDateTimeZone);

        data.put("access_key", paymentGatewayConfig.getAccessKey());
        data.put("profile_id", paymentGatewayConfig.getProfileId());
        data.put("transaction_uuid", uniqueId);
        data.put("signed_field_names", signedWMFieldNames);
        data.put("unsigned_field_names", unsignedWMFieldNames);
        data.put("signed_date_time", signedDateTime);
        data.put("locale", defaultLocale);
        data.put("transaction_type", defaultTransactionType);
        data.put("reference_number", payment.getReferenceNumber());
        data.put("amount", String.valueOf(payment.getAmount()));
        data.put("currency", payment.getCurrency());

        return data;
    }

    private Map<String, String> getFormData(String uniqueId, Payment payment, PaymentGatewayConfig paymentGatewayConfig) {
        Map<String, String> data = new HashMap<>();

        LOGGER.info("getting form data for unique id >> " + uniqueId);

        String signedDateTime = WVRAPUtil.formatDate(new Date(), defaultSignedDateFormat, defaultSignedDateTimeZone);

        data.put("access_key", paymentGatewayConfig.getAccessKey());
        data.put("profile_id", paymentGatewayConfig.getProfileId());
        data.put("transaction_uuid", uniqueId);
        data.put("signed_field_names", signedWMFieldNames);
        data.put("unsigned_field_names", unsignedWMFieldNames);
        data.put("signed_date_time", signedDateTime);
        data.put("locale", defaultLocale);
        data.put("transaction_type", defaultTransactionType);
        data.put("reference_number", payment.getReferenceNumber());
        data.put("amount", String.valueOf(payment.getAmount()));
        data.put("currency", payment.getCurrency());

        return data;
    }

    private Map<String, String> getFormDataTransactionType(String uniqueId, PaymentTokenModel model, PaymentGatewayConfig paymentGatewayConfig,
                                                           String transactionType, String signedFieldNames) {
        Map<String, String> data = new HashMap<>();

        LOGGER.info("getting form data for unique id >> " + uniqueId);

        String signedDateTime = WVRAPUtil.formatDate(new Date(), defaultSignedDateFormat, defaultSignedDateTimeZone);

        data.put("access_key", paymentGatewayConfig.getAccessKey());
        data.put("profile_id", paymentGatewayConfig.getProfileId());
        data.put("transaction_uuid", uniqueId);
        data.put("signed_field_names", signedFieldNames);
        data.put("unsigned_field_names", unsignedWMFieldNames);
        data.put("signed_date_time", signedDateTime);
        data.put("locale", defaultLocale);
        if (StringUtils.isNotEmpty(transactionType)) {
            data.put("transaction_type", transactionType);
        } else {
            data.put("transaction_type", defaultTransactionType);
        }
        data.put("reference_number", model.getReference());
        data.put("amount", String.valueOf(model.getAmount()));
        data.put("currency", model.getCurrency());
        if (StringUtils.isNotEmpty(model.getPaymentToken())) {
            data.put("payment_token", model.getPaymentToken());
        }

        return data;
    }

    private String generateFingerprint(Map<String, String> data, String signedFieldNames, String secretKey) {
        LOGGER.info("Generating Finger Print");

        try {
            String[] signedFieldNamesArray = signedFieldNames.split(",");

            List<String> dataToSignList = new ArrayList<>();

            for (String field : signedFieldNamesArray) {
                String dataToSign = field + "=" + data.get(field);
                dataToSignList.add(dataToSign);
            }

            String dataToSignString = StringUtils.join(dataToSignList, ",");
            LOGGER.info("dataToSignString:" + dataToSignString);

            byte[] bytes = HmacUtils.hmacSha256(secretKey, dataToSignString);

            return new String(Base64.encodeBase64(bytes));

        } catch (Exception e) {
            LOGGER.error("could not generate fingerprint", e);
            throw e;
        }
    }

    private List<String> getExpiryYearList() {
        List<String> years = new ArrayList<>(10);
        DateTime now = new DateTime();
        int startYear = now.getYear();
        for (int i = startYear; i <= startYear + 10; i++) {
            years.add(String.valueOf(i));
        }
        return years;
    }
}
