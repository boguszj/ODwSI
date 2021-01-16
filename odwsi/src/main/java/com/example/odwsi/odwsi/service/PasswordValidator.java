package com.example.odwsi.odwsi.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {

    public boolean validatePassword(String password) {
        return isLongEnough(password) &&
               isNotTooLong(password) &&
               containsCapitalLetter(password) &&
               containsNonCapitalLetter(password) &&
               containsDigit(password) &&
               containsSpecialCharacter(password);
    }

    private boolean isLongEnough(String password) {
        return password.length() > 8;
    }

    private boolean isNotTooLong(String password) {
        return password.length() < 128;
    }

    private boolean containsCapitalLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsNonCapitalLetter(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean containsDigit(String password) {
        return password.matches(".*[0-9].*");
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[~!@#$%^&*()].*");
    }

}
