package com.example.odwsi.odwsi.service.credential;

import com.example.odwsi.odwsi.data.credential.ShareCredentialDto;
import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.repository.credential.CredentialRepository;
import com.example.odwsi.odwsi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ShareCredentialDtoValidator implements Validator {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return ShareCredentialDto.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        ShareCredentialDto shareCredentialDto = (ShareCredentialDto) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "credentialId", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (shareCredentialDto.getCredentialId() == null || shareCredentialDto.getUsername() == null) return;

        if (!isUsernameValid(shareCredentialDto.getUsername())) {
            handleInvalidUsername(errors);
        } else if (isAlreadySharedToUser(shareCredentialDto)) {
            handleIsAlreadySharedToUser(errors);
        }

        if (!isCredentialIdValid(shareCredentialDto.getCredentialId())) {
            handleInvalidCredentialId(errors);
        }
    }

    private boolean isUsernameValid(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean isCredentialIdValid(UUID credentialId) {
        return credentialRepository.existsById(credentialId);
    }

    private boolean isAlreadySharedToUser(ShareCredentialDto shareCredentialDto) {
        User user = userRepository.findByUsername(shareCredentialDto.getUsername()).orElseThrow(EntityNotFoundException::new);
        return user.getCredentials().stream()
                .anyMatch(credential -> credential.getCredentialId().equals(shareCredentialDto.getCredentialId()));
    }

    private void handleInvalidUsername(Errors errors) {
        errors.rejectValue("username", "Share.username");
    }

    private void handleIsAlreadySharedToUser(Errors errors) {
        errors.rejectValue("username", "Share.alreadyShared");
    }

    private void handleInvalidCredentialId(Errors errors) {
        errors.reject("Share.credentialId");
    }
}
