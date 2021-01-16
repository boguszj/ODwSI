package com.example.odwsi.odwsi.service.credential;

import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CredentialAuthorizationProvider {

    private final UserService userService;

    public boolean authorizedToCredential(UUID credentialId) {
        User user = userService.getCurrentUser();
        return user.getCredentials().stream()
                .anyMatch(credential -> credential.getCredentialId().equals(credentialId));
    }

    public boolean ownerOfCredential(UUID credentialId) {
        User user = userService.getCurrentUser();
        return user.getCredentials().stream()
                .filter(credential -> credential.isOwner(user.getUsername()))
                .anyMatch(credential -> credential.getCredentialId().equals(credentialId));
    }

}
