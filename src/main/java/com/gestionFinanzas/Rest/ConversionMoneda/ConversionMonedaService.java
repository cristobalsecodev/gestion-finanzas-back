package com.gestionFinanzas.Rest.ConversionMoneda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.ConversionMoneda.DTOs.ConversionMonedaDto;
import com.gestionFinanzas.Rest.ConversionMoneda.DTOs.RatioConversionDto;
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
public class ConversionMonedaService {

    @Value("${exchangeRate.api.key}")
    private String apiKey;

    @Value("${exchangeRate.baseUrl}")
    private String baseUrl;

    public ConversionMonedaDto conversionMoneda(String moneda) {
        RestTemplate restTemplate = new RestTemplate();

        // Construimos url de la REST API
        String url =
                baseUrl + "/v6/"
                        + apiKey
                        + "/latest/"
                        + moneda;


        try {
            // Devuelve la info
            ResponseEntity<String> apiInfo = restTemplate.getForEntity(url, String.class);

            // Procesamos el JSON
            ObjectMapper mapper = new ObjectMapper();

            // Convertimos la cadena en un árbol de nodos
            JsonNode node = mapper.readTree(apiInfo.getBody());

            // Verificamos si la respuesta es exitosa (código 2xx)
            if(apiInfo.getStatusCode().is2xxSuccessful()) {

                // Instanciamos el DTO
                ConversionMonedaDto conversion = new ConversionMonedaDto();

                // Rellenamos el DTO con la respuesta del JSON
                conversion.setResult(node.get("result").asText());
                conversion.setOriginalCurrency(node.get("base_code").asText());

                // Parseamos las fechas a Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                conversion.setTimeLastUpdate(dateFormat.parse(node.get("time_last_update_utc").asText()));
                conversion.setTimeNextUpdate(dateFormat.parse(node.get("time_next_update_utc").asText()));

                // Mapeamos conversion_rates para convertirlo en el DTO
                List<RatioConversionDto> rates = new ArrayList<>();

                JsonNode conversionRatesNode = node.get("conversion_rates");

                conversionRatesNode.fieldNames().forEachRemaining(currency -> {

                    if(comprobarSiEsMonedaPrincipal(currency)) {
                        RatioConversionDto ratio = new RatioConversionDto();
                        ratio.setCurrencyCode(currency);
                        ratio.setCurrencyName(PrincipalesMonedasENUM.valueOf(currency).getNombre());
                        ratio.setConversionValue(new BigDecimal(conversionRatesNode.get(currency).asText()));
                        rates.add(ratio);
                    }

                });

                conversion.setConversionRates(rates);

                return conversion;

            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException("La conversión de JSON a árbol de nodos ha fallado");
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ApiException(ex.getStatusText(), ex.getStatusCode().value());
        } catch (ParseException e) {
            throw new RuntimeException("La conversión de la fecha a tipo Date ha fallado");
        }

        return new ConversionMonedaDto();

    }

    private static Boolean comprobarSiEsMonedaPrincipal(String currency) {
        try {
            PrincipalesMonedasENUM.valueOf(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
