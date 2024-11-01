package com.gestionFinanzas.Rest.MarketValuesData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.MarketValuesData.DTOs.*;
import com.gestionFinanzas.Shared.DateUtils;
import com.gestionFinanzas.Shared.ExceptionHandler.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MarketValuesDataService {

    @Value("${polygon.api.key}")
    private String apiKey;

    @Value("${polygon.baseUrl}")
    private String baseUrl;

    public StockForDayDto getStockForDay(StockForDayFilterDto filter) {

        RestTemplate restTemplate = new RestTemplate();

        // Construimos url de la REST API
        String url =
                baseUrl + "/v1/open-close/"
                        + filter.getSymbol().toUpperCase()
                        + "/"
                        + DateUtils.dateToString(filter.getDate(), "yyyy-MM-dd")
                        + "?adjusted=true"
                        + "&apikey=" + apiKey;

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
                StockForDayDto stock = new StockForDayDto();

                // Rellenamos el DTO con la respuesta del JSON
                stock.setAfterHours(node.get("afterHours").asText());
                stock.setSymbol(node.get("symbol").asText());
                stock.setStatus(node.get("status").asText());

                stock.setClose(BigDecimal.valueOf(node.get("close").asDouble()));
                stock.setHigh(BigDecimal.valueOf(node.get("high").asDouble()));
                stock.setLow(BigDecimal.valueOf(node.get("low").asDouble()));
                stock.setOpen(BigDecimal.valueOf(node.get("open").asDouble()));
                stock.setPreMarket(BigDecimal.valueOf(node.get("preMarket").asDouble()));

                stock.setVolume(node.get("volume").asLong());

                // Convertimos la fecha a un objeto Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(node.get("from").asText());

                stock.setDate(date);

                return stock;

            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException("The conversion from JSON to a node tree has failed");
        } catch (ParseException e) {
            throw new RuntimeException("The conversion of the date to Date type has failed");
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ApiException(ex.getStatusText(), ex.getStatusCode());
        }

        return new StockForDayDto();

    }
}
