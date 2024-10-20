package com.gestionFinanzas.Rest.ConversionDivisa.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConversionDivisaDto {
    private String resultado;
    private Date fechaUltimaActualizacion;
    private Date fechaSiguienteActualizacion;
    private String divisaOriginal;
    private List<RatioConversionDto> ratiosConversion;
}
