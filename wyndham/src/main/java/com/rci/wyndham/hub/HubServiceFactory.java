package com.rci.wyndham.hub;

import com.wyn.hub.client.service.CRMService;
import com.wyn.hub.client.service.FinanceService;
import com.wyn.hub.client.service.MailerService;
import com.wyn.hub.client.service.SalesService;
import com.wyn.hub.client.service.SecurityService;
import com.wyn.hub.client.service.PaylinkService;


public interface HubServiceFactory {

    MailerService getMailerService();

    SecurityService getSecurityService();

    CRMService getCrmService();

    SalesService getSalesService();

    FinanceService getFinanceService();

    PaylinkService getPaylinkService();

}
