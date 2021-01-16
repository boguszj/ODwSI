package com.example.odwsi.odwsi.data.credential;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.vault.repository.mapping.Secret;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Secret("credential")
public class CredentialVaultEntity {

    @Id
    UUID credentialId;
    String principal;
    String secret;

}
