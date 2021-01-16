package com.example.odwsi.odwsi.security;

import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        if (exception.getCause() instanceof TooManyInvalidLoginAttemptsException) {
            response.sendRedirect("/login?error=" + LoginErrorCode.BLOCKED);
        } else {
            response.sendRedirect("/login?error=" + LoginErrorCode.INVALID);
        }
    }
}
