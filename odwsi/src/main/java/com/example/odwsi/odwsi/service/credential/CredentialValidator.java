package com.example.odwsi.odwsi.service.credential;

import com.example.odwsi.odwsi.data.credential.CredentialDto;
import com.example.odwsi.odwsi.service.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CredentialValidator implements Validator {

    private final PasswordValidator passwordValidator;

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return CredentialDto.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        CredentialDto credentialDto = (CredentialDto) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "principal", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secret", "NotEmpty");

        if (credentialDto.getName() == null || credentialDto.getSecret() == null || credentialDto.getShouldValidate() == null) return;

        if (!credentialDto.getShouldValidate()) return;

        if (!isSecretValid(credentialDto.getSecret())) {
            handleInvalidSecret(errors);
        }
    }

    private boolean isSecretValid(String secret) {
        return passwordValidator.validatePassword(secret);
    }

    private void handleInvalidSecret(Errors errors) {
        errors.rejectValue("principal", "Secret.static");
    }
}

