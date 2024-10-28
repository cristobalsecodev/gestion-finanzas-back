package com.gestionFinanzas.Auth;

import lombok.Data;

@Data
public class TokenResponseDto {

    private String token;

    private long expiresIn;

}
