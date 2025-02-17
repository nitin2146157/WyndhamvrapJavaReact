package com.rci.wyndham.hub.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("mailerWebService")
public class MailerWebService implements MailerService {

    @Value("${coolingoff.hub.base.url}")
    private String coolingoffHubBaseUrl;

    @Override
    public EmailResponse sendMail(EmailMessage emailMessage) {
        RestTemplate restTemplate = new RestTemplate();
        String url = coolingoffHubBaseUrl + "/mailer/send-email";
        HttpEntity<EmailMessage> entity = new HttpEntity<>(emailMessage);
        return restTemplate.exchange(url, HttpMethod.POST, entity, EmailResponse.class).getBody();
    }

    @Override
    public EmailResponse sendMail(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public EmailResponse sendMail(String s, String s1, String s2, String s3, List<File> list) throws IOException {
        return null;
    }

    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        return null;
    }
}
