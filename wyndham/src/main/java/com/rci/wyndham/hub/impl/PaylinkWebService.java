package com.rci.wyndham.hub.impl;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

@Service("paylinkWebService")
public class PaylinkWebService extends BaseObject implements PaylinkService {
    @Value("${hub.ws.direct.client.base.url}")
    private String hubWebServiceBaseURL;

    @Override
    public PaylinkResponse getPayment(Integer integer) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/paylink/getPayment/" + integer;
        LOGGER.info("URL: " + url);

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!")), PaylinkResponse.class).getBody();
    }

    @Override
    public PaylinkResponse updatePaymentStatus(PaylinkRequest paylinkRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/paylink/updatePaymentStatus/";
        LOGGER.info("URL: " + url);
        HttpEntity<PaylinkRequest> request = new HttpEntity<>(paylinkRequest,createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!"));
        return restTemplate.exchange(url, HttpMethod.POST, request, PaylinkResponse.class).getBody();

    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}