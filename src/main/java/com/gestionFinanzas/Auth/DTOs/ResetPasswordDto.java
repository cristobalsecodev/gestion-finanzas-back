package com.gestionFinanzas.Auth.DTOs;

import lombok.Data;

@Data
public class ResetPasswordDto {

    String token;
    String newPassword;

}
