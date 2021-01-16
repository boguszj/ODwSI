package com.example.odwsi.odwsi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationProcessingFilterEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AuthenticationProcessingFilterEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String error = UrlUtils.buildRequestUrl(request);
        String redirect = super.determineUrlToUseForThisRequest(request, response, exception);
        return UriComponentsBuilder.fromPath(redirect).queryParam("error", error).toUriString();
    }
}