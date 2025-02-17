package com.rci.wyndham.hub.impl;

import java.util.List;

@Service("financeWebService")
public class FinanceWebService extends BaseObject implements FinanceService {

    @Value("${hub.ws.direct.client.base.url}")
    private String hubWebServiceBaseURL;

    /**
     * {@inheritDoc}
     */
    @Override
    public Ownership getOwnershipFinanceDetails(String ownerNumber) {
        LOGGER.info("getOwnershipFinanceDetails for " + ownerNumber);
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/finance/get-ownership-finance-details/" + ownerNumber;
        LOGGER.info("URL: " + url);
        return restTemplate.exchange(url, HttpMethod.GET, null, Ownership.class).getBody();
    }

    @Override
    public Finance getOwnerPayments(String s) {
        return null;
    }

    @Override
    public Integer getNumberOfFinanceDefaults(String s, Integer integer) {
        return null;
    }

    @Override
    public Integer getNumberOfLeviesDefaults(String s, Integer integer) {
        return null;
    }

    @Override
    public RepaymentHistory getRepaymentHistory(String s) {
        return null;
    }

    @Override
    public List<Hardship> getHardshipHistory(String s) {
        return null;
    }

}