package com.example.odwsi.odwsi.data.credential;

import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ShareCredentialDto {

    @Setter
    UUID credentialId;
    @NotNull
    String username;

}
