package com.example.odwsi.odwsi.service.credential;

import com.example.odwsi.odwsi.data.credential.CredentialDto;
import com.example.odwsi.odwsi.data.credential.CredentialRepresentation;
import com.example.odwsi.odwsi.data.credential.CredentialVaultEntity;
import com.example.odwsi.odwsi.data.credential.ShareCredentialDto;
import com.example.odwsi.odwsi.model.Credential;
import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.repository.credential.CredentialRepository;
import com.example.odwsi.odwsi.repository.credential.CredentialVaultRepository;
import com.example.odwsi.odwsi.repository.user.UserRepository;
import com.example.odwsi.odwsi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final CredentialVaultRepository credentialVaultRepository;
    private final UserService userService;

    @Transactional
    public void saveCredential(CredentialDto credentialDto) {
        User user = userService.getCurrentUser();

        Credential credential = Credential.builder()
                .name(credentialDto.getName())
                .owner(user.getUsername())
                .build();
        CredentialVaultEntity credentialVaultEntity = CredentialVaultEntity.builder()
                .credentialId(credential.getCredentialId())
                .principal(credentialDto.getPrincipal())
                .secret(credentialDto.getSecret())
                .build();

        user.addCredential(credential);
        credential.addUser(user);

        credentialRepository.save(credential);
        credentialVaultRepository.save(credentialVaultEntity);
    }

    @Transactional
    public Collection<Credential> getCredentialsForCurrentUser() {
        User user = userService.getCurrentUser();
        return credentialRepository.findForUserByUsername(user.getUsername());
    }

    @Transactional
    public CredentialRepresentation getCredentialVaultEntity(UUID credentialId) {
        CredentialVaultEntity credentialVaultEntity = credentialVaultRepository.findById(credentialId).orElseThrow(EntityNotFoundException::new);
        Credential credential = credentialRepository.findById(credentialId).orElseThrow(EntityNotFoundException::new);
        return CredentialRepresentation.builder()
                .credentialId(credentialId)
                .owner(credential.getOwner())
                .name(credential.getName())
                .principal(credentialVaultEntity.getPrincipal())
                .secret(credentialVaultEntity.getSecret())
                .build();
    }

    @Transactional
    public void deleteCredential(UUID credentialId) {
        credentialRepository.findById(credentialId).ifPresent(credentialRepository::delete);
        credentialVaultRepository.findById(credentialId).ifPresent(credentialVaultRepository::delete);
    }

    @Transactional
    public void shareCredential(ShareCredentialDto shareCredentialDto) {
        Credential credential = credentialRepository.findById(shareCredentialDto.getCredentialId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByUsername(shareCredentialDto.getUsername()).orElseThrow(EntityNotFoundException::new);
        user.getCredentials().add(credential);
        credential.addUser(user);
        userRepository.save(user);
    }

}
