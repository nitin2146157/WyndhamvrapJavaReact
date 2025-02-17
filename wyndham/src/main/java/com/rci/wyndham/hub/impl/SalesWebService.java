package com.rci.wyndham.hub.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("salesWebService")
public class SalesWebService extends BaseObject implements SalesService {

    @Value("${hub.ws.direct.client.base.url}")
    private String hubWebServiceBaseURL;

    @Value("${coolingoff.hub.base.url}")
    private String coolingoffHubBaseUrl;

    @Override
    public HubResponse<List<WynContract>> getOwnerContracts(String ownerNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/sales/getOwnerContracts/" + ownerNumber;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HubResponse<List<WynContract>>>() {}).getBody();
    }

    @Override
    public HubResponse<List<ContractST>> getContractsByOwnerSDB(String s) {
        return null;
    }

    @Override
    public HubResponse<List<ContractST>> getContractsByIdSDB(String s) {
        return null;
    }

    @Override
    public HubResponse<WynContract> getContractDetails(String contractNumber) {
        LOGGER.info("getContractDetails for " + contractNumber);
        RestTemplate restTemplate = new RestTemplate();
        String url = coolingoffHubBaseUrl + "/sales/getContractDetails/" + contractNumber;

        LOGGER.info("URL: " + url);
        //return restTemplateBuilder.getRestTemplate().exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HubResponse<WynContract>>(){}).getBody();
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HubResponse<WynContract>>(){}).getBody();
    }


    @Override
    public HubResponse<String> rescindVeniceContract(VeniceContract veniceContract) {
        LOGGER.info("rescindContract for contract:" + veniceContract.getContractNumber());
        String url = coolingoffHubBaseUrl + "/sales/rescindVeniceContract";
        //String url= "http://venicews.ausnz.trendwest.com.au/api/rescissionws";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<VeniceContract> entity = new HttpEntity<>(veniceContract);
        return restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HubResponse<String>>(){}).getBody();
    }

    @Override
    public HubResponse<WynContract> getOwnerRecentContract(String s) {
        return null;
    }

    @Override
    public HubResponse<BigDecimal> getOwnerCreditPriceFreeze(String s) {
        return null;
    }

    @Override
    public HubResponse<Integer> getOwnerNumberOfUpgrades(String s) {
        return null;
    }

    @Override
    public HubResponse<WynSite> getSiteDetails(String s) {
        return null;
    }

    @Override
    public List<ContractST> getContractsByDateRange(Date date, Date date1) {
        return null;
    }

    @Override
    public HubResponse<Calculator> getCalculatorDetails(String s) {
        return null;
    }

    @Override
    public HubResponse<List<HemDetail>> getHemDetails() {
        return null;
    }

    @Override
    public HubResponse<List<WynSalesPerson>> getSalesReps(String s) {
        return null;
    }

    @Override
    public HubResponse<List<WynSalesPerson>> getSalesManagers(String s) {
        return null;
    }

    @Override
    public HubResponse<Map<RecalcScenarioEnum, Calculator>> getReCalculateOptions(String s, BigDecimal bigDecimal) {
        return null;
    }

    @Override
    public HubResponse<WynCurrencyRate> getCurrencyRate(String s, String s1) {
        return null;
    }
}