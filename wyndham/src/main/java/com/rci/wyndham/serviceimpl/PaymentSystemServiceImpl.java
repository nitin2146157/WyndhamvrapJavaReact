package com.rci.wyndham.serviceimpl;

import com.google.gson.Gson;
import com.rci.wyndham.entity.NabDirectPostTransactions;
import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.service.PaymentSystemService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.List;

@Service
public class PaymentSystemServiceImpl extends BaseObject implements PaymentSystemService {

    public static final String EXPRESS_CHECK_IN = "EXPRESS_CHECK_IN";
    public static final String EXPRESS_CHECK_IN_CREATED_BY = "EXPRESS_CHECK_IN_REGISTRATION";
    public static final String TOKEN = "TOKEN";
    public static final String PAYMENT_SYSTEM_ADD_URL = "https://login.wyndhamap.com/wynpay/api/payment/add";

    public static final String PAYMENT_SYSTEM_GET_URL = "https://hub.wyndhamap.com/hub-api/v1_pay/payment/getPaymentInfo/express_check_in/";

    @Override
    public boolean saveToken(NabDirectPostTransactions transaction, String paymentToken, String clientIp, String cardNumber,
                             String cardTypeValue, String expiryMm, String expiryYy) {

        try {
            // creating the headers
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));

            PaymentOrder paymentOrder = getPaymentOrder(transaction.getReference(), clientIp, paymentToken, cardNumber,
                    cardTypeValue, expiryMm, expiryYy);

            RestResponse<String> response = doAddCall(PAYMENT_SYSTEM_ADD_URL, paymentOrder);
            if(response != null) {
                String status = response.getStatus();
                if(StringUtils.equals(status, "SUCCESS")) {
                    return true;
                }
                LOGGER.error("could not save token >> " + response.getMessage());
            }
            return false;
        } catch (RestClientException e) {
            LOGGER.error("Exception in rest Consumer post", e);
            return false;
        }
    }

    @Override
    public String getToken(String ownerNumber) {
        String url = PAYMENT_SYSTEM_GET_URL+ownerNumber;
        PaymentMode paymentMode = doGetCall(url, ownerNumber);
        if(paymentMode!=null && paymentMode.getTokenInformation()!=null && StringUtils.isNotEmpty(paymentMode.getTokenInformation().getToken())) {
            return paymentMode.getTokenInformation().getToken();
        } else {
            return null;
        }
    }

    public PaymentOrder getPaymentOrder(String ownerNumber, String clientIp, String paymentToken, String cardNumber,
            String cardTypeValue, String expiryMm, String expiryYy) {
        PaymentOrder paymentOrder = new PaymentOrder();

        ClientDetail clientDetail = new ClientDetail();
        clientDetail.setName(EXPRESS_CHECK_IN);
        paymentOrder.setClientDetail(clientDetail);

        paymentOrder.setOwnerNumber(ownerNumber);


        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(TOKEN);
        paymentOrder.setPaymentMethod(paymentMethod);

        Token tokenDetail = new Token();
        tokenDetail.setToken(paymentToken);
        tokenDetail.setCardLast4Digits(cardNumber);
        tokenDetail.setExpiryMm(expiryMm);
        tokenDetail.setExpiryYy(expiryYy);

        CardType cardType = new CardType();
        cardType.setName(cardTypeValue);
        tokenDetail.setCardType(cardType);

        paymentOrder.setTokenDetail(tokenDetail);
        paymentOrder.setCreatedBy(EXPRESS_CHECK_IN_CREATED_BY);
        paymentOrder.setSmsConsent(true);
        paymentOrder.setClientIp(clientIp);

        return paymentOrder;
    }

    public RestResponse<String> doAddCall(final String url, PaymentOrder paymentOrder) {
        Gson gson = new Gson();
        String json = gson.toJson(paymentOrder);
        LOGGER.info("TOKEN INPUT IS: "+json);
        try {
            RestTemplate restTemplate = new RestTemplateBuilder().getRestTemplate();
            LOGGER.info("setting up token at url: "+url+" | details >> " +paymentOrder.getOwnerNumber());
            RestResponse<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(paymentOrder),
                    new ParameterizedTypeReference<RestResponse<String>>() {
                    }).getBody();
            return response;
        } catch (Exception e) {
            LOGGER.error("error while setting up autopay", e);
            throw new RuntimeException(e);
        }
    }

    public PaymentMode doGetCall(final String url, String ownerNumber) {
        try {
            RestTemplate restTemplate = new RestTemplateBuilder().getRestTemplate();
            LOGGER.info("setting up token at url: "+url+" | ownerNumber >> " +ownerNumber);
            com.wyn.wyndhamvrap.ws.client.autopay.HubResponse hubResponse = restTemplate.getForObject(url, com.wyn.wyndhamvrap.ws.client.autopay.HubResponse.class);
            if(hubResponse.getData()!=null) {
                List<PaymentMode> paymentModes = (List<PaymentMode>)hubResponse.getData();
                if(CollectionUtils.isNotEmpty(paymentModes)) {
                    return paymentModes.get(0);
                }
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("error while getting token", e);
            throw new RuntimeException(e);
        }
    }
}

