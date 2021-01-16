package com.example.odwsi.odwsi.repository.credential;

import com.example.odwsi.odwsi.data.credential.CredentialVaultEntity;
import com.example.odwsi.odwsi.vault.VaultRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@VaultRepository
public interface CredentialVaultRepository extends CrudRepository<CredentialVaultEntity, UUID> {
}
