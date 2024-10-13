package com.gestionFinanzas.Rest.DatosMercadoValores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.*;
import com.gestionFinanzas.Rest.DatosMercadoValores.ENUMs.OutputSizeENUM;
import com.gestionFinanzas.Rest.DatosMercadoValores.ENUMs.TipoPeriodoENUM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DatosMercadoValoresService {

    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    @Value("${alpha.vantage.baseUrl}")
    private String baseUrl;

    public ActivoDTO obtenerInfoActivo(FiltroActivoDTO filtro) {

        RestTemplate restTemplate = new RestTemplate();

        // Comprobamos si el tipo de periodo es correcto
        comprobarParametros(filtro);

        // Construimos url de la REST API
        String url = baseUrl + "?function=" + construirParametrosUrl(filtro) + "&apikey=" + apiKey;

        // Devuelve la info
        String apiInfo = restTemplate.getForObject(url, String.class);

        // Creamos los objetos para el mapeo del JSON
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parseamos la información a JSON para poder trabajar con ella
            JsonNode rootNode = objectMapper.readTree(apiInfo);

            // Accedemos a los datos y los añadimos al DTO
            ActivoDTO activo = new ActivoDTO();
            activo.setInformation(rootNode.path("Meta Data").path("1. Information").asText());
            activo.setSymbol(rootNode.path("Meta Data").path("2. Symbol").asText());
            activo.setLastRefreshed(dateFormat.parse(rootNode.path("Meta Data").path("3. Last Refreshed").asText()));
            activo.setOutputSize(rootNode.path("Meta Data").path("4. Output Size").asText());
            activo.setTimeZone(rootNode.path("Meta Data").path("5. Time Zone").asText());

            // Procedimiento para añadir cada fecha a la lista del DTO
            List<ValoresMercadoDto> dailyValuesList = new ArrayList<>();
            JsonNode timeSeriesNode = rootNode.path(pathPorTipoPeriodo(filtro.getPeriodType()));

            Iterator<String> dates = timeSeriesNode.fieldNames();

            while (dates.hasNext()) {
                String datesString = dates.next();
                Date date = dateFormat.parse(datesString);

                // Condición que acota el rango en caso de que alguna de las fechas no sea null
                if((filtro.getStartDate() == null || !date.before(filtro.getStartDate()))
                    && (filtro.getEndDate() == null || !date.after(filtro.getEndDate()))) {

                    JsonNode dailyDataNode = timeSeriesNode.path(datesString);

                    ValoresMercadoDto dailyValues = new ValoresMercadoDto();
                    dailyValues.setDate(date);
                    dailyValues.setOpen(new BigDecimal(dailyDataNode.path("1. open").asText()));
                    dailyValues.setHigh(new BigDecimal(dailyDataNode.path("2. high").asText()));
                    dailyValues.setLow(new BigDecimal(dailyDataNode.path("3. low").asText()));
                    dailyValues.setClose(new BigDecimal(dailyDataNode.path("4. close").asText()));
                    dailyValues.setVolume(dailyDataNode.path("5. volume").asLong());

                    dailyValuesList.add(dailyValues);

                }
            }

            activo.setDailyValues(dailyValuesList);

            return activo;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("El parseo a JSON no ha podido realizarse");
        } catch (ParseException e) {
            throw new RuntimeException("No se ha podido parsear la fecha de String a Date");
        }

    }

    private void comprobarParametros(FiltroActivoDTO filtro) {

        if(!filtro.getPeriodType().equalsIgnoreCase(TipoPeriodoENUM.DAILY.getValue())
            && !filtro.getPeriodType().equalsIgnoreCase(TipoPeriodoENUM.WEEKLY.getValue())
            && !filtro.getPeriodType().equalsIgnoreCase(TipoPeriodoENUM.MONTHLY.getValue()))
        {
            throw new RuntimeException("El tipo de periodo marcado no coincide con los establecidos (DAILY, WEEKLY, MONTHLY)");
        }

        if(filtro.getPeriodType().equalsIgnoreCase(TipoPeriodoENUM.DAILY.getValue())
            && (!filtro.getOutputSize().equalsIgnoreCase(OutputSizeENUM.COMPACT.getValue()))
                && (!filtro.getOutputSize().equalsIgnoreCase(OutputSizeENUM.FULL.getValue())))
        {
            throw new RuntimeException("El tipo de histórico marcado no coincide con los establecidos (COMPACT, FULL)");
        }

    }

    private String construirParametrosUrl(FiltroActivoDTO filtro) {

        String outputSize = filtro.getPeriodType().equals(TipoPeriodoENUM.DAILY.getValue()) ?  "&outputsize=" + filtro.getOutputSize().toLowerCase() : "";
        return filtro.getPeriodType().toUpperCase() + "&symbol=" + filtro.getSymbol() + outputSize;

    }

    private String pathPorTipoPeriodo(String periodType) {

        if(periodType.equalsIgnoreCase(TipoPeriodoENUM.DAILY.getValue())) {
            return "Time Series (Daily)";
        } else if(periodType.equalsIgnoreCase(TipoPeriodoENUM.WEEKLY.getValue())) {
            return "Weekly Time Series";
        } else if(periodType.equalsIgnoreCase(TipoPeriodoENUM.MONTHLY.getValue())) {
            return "Monthly Time Series";
        } else {
            throw new RuntimeException("Tipo de periodo no válido");
        }

    }

}
