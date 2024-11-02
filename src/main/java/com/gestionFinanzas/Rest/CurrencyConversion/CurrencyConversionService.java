package com.gestionFinanzas.Rest.CurrencyConversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.CurrencyConversion.DTOs.CurrencyConversionDto;
import com.gestionFinanzas.Rest.CurrencyConversion.DTOs.ExchangeRatioDto;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ApiException;
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
public class CurrencyConversionService {

    @Value("${exchangeRate.api.key}")
    private String apiKey;

    @Value("${exchangeRate.baseUrl}")
    private String baseUrl;

    public CurrencyConversionDto currencyConversion(String currency) {
        RestTemplate restTemplate = new RestTemplate();

        // Construimos url de la REST API
        String url =
                baseUrl + "/v6/"
                        + apiKey
                        + "/latest/"
                        + currency;


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
                CurrencyConversionDto currencyConversion = new CurrencyConversionDto();

                // Rellenamos el DTO con la respuesta del JSON
                currencyConversion.setResult(node.get("result").asText());
                currencyConversion.setOriginalCurrency(node.get("base_code").asText());

                // Parseamos las fechas a Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                currencyConversion.setLastUpdatedDate(dateFormat.parse(node.get("time_last_update_utc").asText()));
                currencyConversion.setNextUpdateDate(dateFormat.parse(node.get("time_next_update_utc").asText()));

                // Mapeamos conversion_rates para convertirlo en el DTO
                List<ExchangeRatioDto> ratios = new ArrayList<>();

                JsonNode ratiosConversionNodo = node.get("conversion_rates");

                ratiosConversionNodo.fieldNames().forEachRemaining(currencyNode -> {

                    if(checkIfMajorCurrency(currencyNode)) {
                        ExchangeRatioDto ratio = new ExchangeRatioDto();
                        ratio.setCurrencyCode(currencyNode);
                        ratio.setCurrencyName(MajorCurrenciesEnum.valueOf(currencyNode).getName());
                        ratio.setExchangeRate(new BigDecimal(ratiosConversionNodo.get(currencyNode).asText()));
                        ratios.add(ratio);
                    }

                });

                currencyConversion.setExchangeRate(ratios);

                return currencyConversion;

            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException("The conversion from JSON to a node tree has failed");
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ApiException(ex.getStatusText(), ex.getStatusCode());
        } catch (ParseException e) {
            throw new RuntimeException("The conversion of the date to Date type has failed");
        }

        return new CurrencyConversionDto();

    }

    private static Boolean checkIfMajorCurrency(String currency) {
        try {
            MajorCurrenciesEnum.valueOf(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
