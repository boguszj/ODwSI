package com.example.odwsi.odwsi.security;

import org.springframework.security.core.AuthenticationException;

public class TooManyInvalidLoginAttemptsException extends AuthenticationException {
    public TooManyInvalidLoginAttemptsException(String msg) {
        super(msg);
    }
}
