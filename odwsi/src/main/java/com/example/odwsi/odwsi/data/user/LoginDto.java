package com.example.odwsi.odwsi.data.user;

import lombok.Data;

@Data
public class LoginDto {
    String username;
    String password;
    String error;
}
