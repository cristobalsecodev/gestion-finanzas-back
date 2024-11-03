package com.gestionFinanzas.Auth.DTOs;

import lombok.Data;

@Data
public class WantsResetPasswordDto {

    String email;

    String url;

}
