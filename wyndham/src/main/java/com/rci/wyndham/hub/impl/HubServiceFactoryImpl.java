package com.rci.wyndham.hub.impl;

import com.rci.wyndham.hub.HubServiceFactory;
import com.wyn.hub.client.service.*;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("hubService")
@Component
public class HubServiceFactoryImpl implements HubServiceFactory {

    @Autowired
    private ApplicationContext context;

    //private StaffService staffService;

    @Autowired
    @Qualifier("mailerWebService")
    private MailerService mailerService;

    @Autowired
    @Qualifier("securityWebService")
    private SecurityService securityService;

    @Autowired
    @Qualifier("crmWebService")
    private CRMService crmService;

    @Autowired
    @Qualifier("salesWebService")
    private SalesService salesService;

    @Autowired
    @Qualifier("financeWebService")
    private FinanceService financeService;

    @Autowired
    @Qualifier("paylinkWebService")
    private PaylinkService paylinkService;

    @PostConstruct
    public void init() {
    }

    @Override
    public MailerService getMailerService() {
        return mailerService;
    }


    @Override
    public SecurityService getSecurityService() {
        return securityService;
    }

    @Override
    public CRMService getCrmService() {
        return crmService;
    }

    @Override
    public SalesService getSalesService() {
        return salesService;
    }

    @Override
    public FinanceService getFinanceService() {
        return financeService;
    }

    @Override
    public PaylinkService getPaylinkService() {
        return paylinkService;
    }
}