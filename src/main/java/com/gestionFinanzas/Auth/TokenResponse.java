package com.gestionFinanzas.Auth;

import lombok.Data;

@Data
public class TokenResponse {

    private String token;

    private long expiresIn;

}
