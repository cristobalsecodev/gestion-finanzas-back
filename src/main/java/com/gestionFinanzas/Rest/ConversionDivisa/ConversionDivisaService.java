package com.gestionFinanzas.Rest.ConversionDivisa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.ConversionDivisa.DTOs.ConversionDivisaDto;
import com.gestionFinanzas.Rest.ConversionDivisa.DTOs.RatioConversionDto;
import com.gestionFinanzas.Shared.ManejoExcepciones.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversionDivisaService {

    @Value("${exchangeRate.api.key}")
    private String apiKey;

    @Value("${exchangeRate.baseUrl}")
    private String baseUrl;

    public ConversionDivisaDto conversionDivisa(String divisa) {
        RestTemplate restTemplate = new RestTemplate();

        // Construimos url de la REST API
        String url =
                baseUrl + "/v6/"
                        + apiKey
                        + "/latest/"
                        + divisa;


        try {
            // Devuelve la info
            ResponseEntity<String> apiInfo = restTemplate.getForEntity(url, String.class);

            // Procesamos el JSON
            ObjectMapper mapper = new ObjectMapper();

            // Convertimos la cadena en un árbol de nodos
            JsonNode nodo = mapper.readTree(apiInfo.getBody());

            // Verificamos si la respuesta es exitosa (código 2xx)
            if(apiInfo.getStatusCode().is2xxSuccessful()) {

                // Instanciamos el DTO
                ConversionDivisaDto conversion = new ConversionDivisaDto();

                // Rellenamos el DTO con la respuesta del JSON
                conversion.setResultado(nodo.get("result").asText());
                conversion.setDivisaOriginal(nodo.get("base_code").asText());

                // Parseamos las fechas a Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                conversion.setFechaUltimaActualizacion(dateFormat.parse(nodo.get("time_last_update_utc").asText()));
                conversion.setFechaSiguienteActualizacion(dateFormat.parse(nodo.get("time_next_update_utc").asText()));

                // Mapeamos conversion_rates para convertirlo en el DTO
                List<RatioConversionDto> ratios = new ArrayList<>();

                JsonNode ratiosConversionNodo = nodo.get("conversion_rates");

                ratiosConversionNodo.fieldNames().forEachRemaining(divisaNodo -> {

                    if(comprobarSiEsDivisaPrincipal(divisaNodo)) {
                        RatioConversionDto ratio = new RatioConversionDto();
                        ratio.setCodigoDivisa(divisaNodo);
                        ratio.setNombreDivisa(PrincipalesDivisasENUM.valueOf(divisaNodo).getNombre());
                        ratio.setValorConversion(new BigDecimal(ratiosConversionNodo.get(divisaNodo).asText()));
                        ratios.add(ratio);
                    }

                });

                conversion.setRatiosConversion(ratios);

                return conversion;

            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException("La conversión de JSON a árbol de nodos ha fallado");
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ApiException(ex.getStatusText(), ex.getStatusCode().value());
        } catch (ParseException e) {
            throw new RuntimeException("La conversión de la fecha a tipo Date ha fallado");
        }

        return new ConversionDivisaDto();

    }

    private static Boolean comprobarSiEsDivisaPrincipal(String divisa) {
        try {
            PrincipalesDivisasENUM.valueOf(divisa);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
