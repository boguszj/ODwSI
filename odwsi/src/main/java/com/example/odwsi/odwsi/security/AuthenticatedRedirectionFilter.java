package com.example.odwsi.odwsi.security;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticatedRedirectionFilter extends GenericFilterBean {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if (isAuthenticated() && isNoAuthenticationUrl(servletRequest)) {
            String encodedRedirectURL = ((HttpServletResponse) response).encodeRedirectURL(servletRequest.getContextPath() + "/dashboard");

            servletResponse.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            servletResponse.setHeader("Location", encodedRedirectURL);
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    private boolean isNoAuthenticationUrl(HttpServletRequest servletRequest) {
        return "/login".equals(servletRequest.getRequestURI()) || "/registration".equals(servletRequest.getRequestURI());
    }

}