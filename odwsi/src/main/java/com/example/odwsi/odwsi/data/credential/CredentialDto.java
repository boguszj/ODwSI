package com.example.odwsi.odwsi.data.credential;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CredentialDto {

    @NotNull
    String name;
    @NotNull
    String principal;
    @NotNull
    String secret;
    @NotNull
    Boolean shouldValidate;

}
