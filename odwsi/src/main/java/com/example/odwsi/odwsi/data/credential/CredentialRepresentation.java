package com.example.odwsi.odwsi.data.credential;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CredentialRepresentation {

    UUID credentialId;
    String owner;
    String name;
    String principal;
    String secret;

}
