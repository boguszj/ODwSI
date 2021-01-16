package com.example.odwsi.odwsi.vault;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.repository.configuration.EnableVaultRepositories;

import java.net.URI;


@Configuration
@EnableVaultRepositories(value = "com.example", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = VaultRepository.class))
public class VaultConfiguration {

    @Value(value = "${vault.uri}")
    String uri;

    @Value(value = "${vault.token}")
    String token;

    @Bean
    @SneakyThrows
    public VaultTemplate vaultTemplate() {
        return new VaultTemplate(
                VaultEndpoint.from(new URI(uri)),
                new TokenAuthentication(token)
        );
    }

}
