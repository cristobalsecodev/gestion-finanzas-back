package com.gestionFinanzas.Rest.DatosMercadoValores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.*;
import com.gestionFinanzas.Shared.DateUtils;
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
import java.util.Date;

@Service
public class DatosMercadoValoresService {

    @Value("${polygon.api.key}")
    private String apiKey;

    @Value("${polygon.baseUrl}")
    private String baseUrl;

    public ActivoDiaUnicoDto obtenerInfoActivo(FiltroActivoDiaUnicoDto filtro) {

        RestTemplate restTemplate = new RestTemplate();

        // Construimos url de la REST API
        String url =
                baseUrl + "/v1/open-close/"
                        + filtro.getSymbol().toUpperCase()
                        + "/"
                        + DateUtils.dateToString(filtro.getDate(), "yyyy-MM-dd")
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
                ActivoDiaUnicoDto activo = new ActivoDiaUnicoDto();

                // Rellenamos el DTO con la respuesta del JSON
                activo.setAfterHours(node.get("afterHours").asText());
                activo.setSymbol(node.get("symbol").asText());
                activo.setStatus(node.get("status").asText());

                activo.setClose(BigDecimal.valueOf(node.get("close").asDouble()));
                activo.setHigh(BigDecimal.valueOf(node.get("high").asDouble()));
                activo.setLow(BigDecimal.valueOf(node.get("low").asDouble()));
                activo.setOpen(BigDecimal.valueOf(node.get("open").asDouble()));
                activo.setPreMarket(BigDecimal.valueOf(node.get("preMarket").asDouble()));

                activo.setVolume(node.get("volume").asLong());

                // Convertimos la fecha a un objeto Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(node.get("from").asText());

                activo.setDate(date);

                return activo;

            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException("La conversión de JSON a árbol de nodos ha fallado");
        } catch (ParseException e) {
            throw new RuntimeException("La conversión de la fecha a tipo Date ha fallado");
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ApiException(ex.getStatusText(), ex.getStatusCode().value());
        }

        return new ActivoDiaUnicoDto();

    }
}
