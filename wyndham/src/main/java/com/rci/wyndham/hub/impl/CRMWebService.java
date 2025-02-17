package com.rci.wyndham.hub.impl;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service("crmWebService")
public class CRMWebService implements CRMService {

    public final static Logger LOGGER = LoggerFactory.getLogger(CRMWebService.class);

    @Value("${hub.ws.direct.client.base.url}")
    private String hubWebServiceBaseURL;

    @Autowired
    private MailerService mailerService;

    public static final String EMAIL_FROM = "CFCampaigns@wyn.com";
    public static final String EMAIL_TO = "CFCampaigns@wyn.com";
    public static final String CLIENT_NAME = "Supersite";

    /**
     * {@inheritDoc}
     */
    @Override
    public HubResponse<WynMembership> getMembershipDetails(String ownerNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/crm/membership/" + ownerNumber;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HubResponse<WynMembership>>() {}).getBody();
    }

    @Override
    public HubResponse<MembershipTypeEnum> getMembershipType(String s) {
        return null;
    }

    @Override
    public HubResponse<Integer> createPerson(WynPerson wynPerson) {
        return null;
    }

    @Override
    public HubResponse<Integer> editPerson(WynPerson wynPerson) {
        return null;
    }

    @Override
    public HubResponse<WynPerson> getPersonDetails(Integer integer) {
        return null;
    }

    @Override
    public HubResponse<List<WynState>> getStates(String s) {
        return null;
    }

    @Override
    public HubResponse<List<WynState>> getStatesAll() {
        return null;
    }

    @Override
    public HubResponse<List<WynCountry>> getCountries() {
        return null;
    }

    @Override
    public OwnerCredits readBonusOwnerCredits(String s) {
        return null;
    }

    @Override
    public HubResponse<Boolean> clubAsiaMembershipExists(String s) {
        return null;
    }

    @Override
    public HubResponse<Boolean> ownerDetailsChange(PersonDetailsChangeRequest personDetailsChangeRequest) {
        return null;
    }

    @Override
    public LeviesStatement[] getLeviStatements(String ownerNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/crm/levi-statements/" + ownerNumber;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<LeviesStatement[]>() {}).getBody();
    }

    @Override
    public LeviesStatement getLeviStatement(String ownerNumber, Integer billingYear) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/crm/levi-statement/" + ownerNumber + "/" + billingYear;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<LeviesStatement>() {}).getBody();
    }

    @Override
    public HubResponse<String> getPeriodicStatement(String ownerNumber, Integer year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/crm/statement/periodic/pdf-base64/" + ownerNumber + "/" + year;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HubResponse<String>>() {}).getBody();
    }

    @Override
    public HubResponse<String> submitMarketingConsentForm(CWAMarketingConsentForm cwaMarketingConsentForm) {

//        HubResponse<String>  response = new HubResponse<String>();
//        val.setData(new Boolean("true"));
        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<CWAMarketingConsentForm> entity  = new HttpEntity<CWAMarketingConsentForm>(cwaMarketingConsentForm, createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!"));
        HttpHeaders headers =  createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!");
        // headers.setContentType(MediaType.APPLICATION_XML);
        String url = hubWebServiceBaseURL + "/crm/marketing/consent/form";
        HttpEntity<CWAMarketingConsentForm> entity = new HttpEntity<>(cwaMarketingConsentForm, headers);
//        restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HubResponse<Boolean>>() {}).getBody();
//        HubResponse<Boolean> res = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HubResponse<Boolean>>() {}).getBody();
//        LOGGER.info("response: " + res.getMessage());
        try{
            HubResponse<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HubResponse<String>>() {}).getBody();
            return response;
        }
        catch (Exception e) {
            return null;
        }

//        LOGGER.info("response2: " + res.getStatus());
//        LOGGER.info("response3: " + res.getData());
//          return  res;
//                restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HubResponse<Boolean>>() {}).getBody();
//        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!")), new ParameterizedTypeReference<HubResponse<Boolean>>() {},entity).getBody();
    }


    @Override
    public HubResponse<String> getBase64LeviStatement(String ownerNumber, Integer year) {
        RestTemplate restTemplate = new RestTemplate();
        //HttpHeaders headers =  createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!");
        //HttpEntity<> entity = new HttpEntity<>(headers);
        String url = hubWebServiceBaseURL + "/crm/levies/statement/pdf-base64/" + ownerNumber + "/" + year;
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders("WYN_NEW_WYNDHAM_AP","WYNdham123!")), new ParameterizedTypeReference<HubResponse<String>>() {}).getBody();
    }


    public Boolean sendEmailNotification(String memberNumber, Integer billingYear, byte[] pdfAttachment) {
        try {
            LOGGER.info("Preparing email notification. ");
            String body = memberNumber + " has requested to post hard copy of levies notification. See attached for the copy.";
            String subject = "Request to Post Levies Notification for " + memberNumber;

            LOGGER.info("Preparing emailMessage... ");
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setFromEmail(EMAIL_FROM);
            emailMessage.setClientName(CLIENT_NAME);
            //Prepare the recipients list, only one in this case

            LOGGER.info("Preparing recipients... ");
            List<EmailRecipient> recipients = new ArrayList<EmailRecipient>();
            LOGGER.info("Preparing recipient... ");
            EmailRecipient recipient = new EmailRecipient();
            recipient.setType(EmailRecipient.Type.TO);
            recipient.setEmail(EMAIL_TO);
            LOGGER.info("Adding recipient... ");
            recipients.add(recipient);

            LOGGER.info("Preparing attachments... ");
            List<EmailAttachments> attachments = new ArrayList<>();;
            EmailAttachments attachment = new EmailAttachments();

            attachment.setContent(pdfAttachment);
            attachment.setType("application/pdf");
            attachment.setName(memberNumber + "-" + billingYear + ".pdf");
            attachments.add(attachment);

            LOGGER.info("Adding emailMessage content... ");
            emailMessage.setRecipients(recipients);
            emailMessage.setSubject(subject);
            emailMessage.setMailerType(EmailMessage.MailerType.SMTP);
            emailMessage.setHtml(body);
            emailMessage.setAttachments(attachments);

            LOGGER.info("Sending email to " + EMAIL_TO);

            EmailResponse emailResponse = mailerService.sendMail(emailMessage);
            LOGGER.info("Email response status>>> " + emailResponse.getStatus());
            EmailResponse.Status responseStatus = emailResponse.getStatus();;

            if (!responseStatus.equals(EmailResponse.Status.SENT)) {
                LOGGER.error("Failed to send email to " + EMAIL_TO);
                throw new Exception("Error sending email to " + EMAIL_TO);
            }
            return true;
        }
        catch (Exception e) {
            LOGGER.error("[ownerNumber: {" +  memberNumber +  "}] - Error while sending a levies statement for owner. " + e);
            return false;
        }
    }


    private HttpHeaders createHeaders(String username, String password){

        return new HttpHeaders() {{
            String auth = username + ":" + password;
            //            Charset utf8 = Charset.forName("UTF-8");
            //            MediaType mediaType = new MediaType("text", "html", utf8);
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            //            set( "ContentType", "" );
        }};
    }
}
