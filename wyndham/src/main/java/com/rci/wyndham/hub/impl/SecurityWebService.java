package com.rci.wyndham.hub.impl;

import java.util.List;
import java.util.Map;

@Service("securityWebService")
public class SecurityWebService implements SecurityService {

    @Value("${hub.ws.direct.client.base.url}")
    private String hubWebServiceBaseURL;

    @Override
    public Map<String, List<String>> getInternalUserAuthorizations(String s, String s1) {
        return null;
    }

    @Override
    public List<String> getInternalUserRoles(String s, String s1) {
        return null;
    }

    @Override
    public AuthenticationStatus externalAuthentication(String ownerNumber, String password, String userType) {
        RestTemplate restTemplate = new RestTemplate();
        String url = hubWebServiceBaseURL + "/security/externalAuthentication/" + ownerNumber + "/" + password + "/" + userType;
        return restTemplate.exchange(url, HttpMethod.GET, null, AuthenticationStatus.class).getBody();
    }

    @Override
    public ExternalUserStatus createExternalUser(String s, String s1, String s2) {
        return null;
    }

    @Override
    public ExternalUserStatus changeExternalUserPassword(String s, String s1, String s2) {
        return null;
    }

    @Override
    public ExternalUserStatus changeExternalUserPassword(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public StaffResponseDto authenticateStaff(String s, String s1) {
        return null;
    }

}