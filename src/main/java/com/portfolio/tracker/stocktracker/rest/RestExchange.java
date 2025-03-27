package com.portfolio.tracker.stocktracker.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.tracker.stocktracker.dto.AnnualStatementDTO;
import com.portfolio.tracker.stocktracker.dto.DailyPricesDTO;
import com.portfolio.tracker.stocktracker.util.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public  class RestExchange {

    public static RestTemplate restTemplate = new RestTemplate();

    public static List<DailyPricesDTO> getDecadeData(String ticker,String apiKey, String hostUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Token " + apiKey);
        headers.set("Content-Type","application/json");

        LocalDate today = LocalDate.now();

        // Get the date 10 years ago
        LocalDate tenYearsAgo = today.minusYears(10);

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format both dates
        String startDate = tenYearsAgo.format(formatter);
        String endDate = today.format(formatter);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<DailyPricesDTO>> response = restTemplate.exchange(hostUrl +
                "/tiingo/daily/" + ticker + "/prices" +
                "?startDate=" + startDate
                + "&endDate=" + endDate, HttpMethod.GET, entity, new ParameterizedTypeReference<List<DailyPricesDTO>>() {
        });
        return response.getBody();
    }

    public static List<AnnualStatementDTO> getFundamentals(String ticker,String apiKey, String hostUrl, String startDate){
        ResponseEntity<String> response = restTemplate.exchange(hostUrl +
                "?ticker=" + ticker +
                "&filling_date.gt=" + startDate
                + "&timeframe=annual&order=asc&limit=100&" +
                "apiKey="+apiKey
                , HttpMethod.GET, new HttpEntity<>("body"), String.class);

        try{
            return Mapper.mapFinancials(response.getBody());
        }catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

   public static String getEstimates(String ticker,String apiKey, String hostUrl){
        HttpHeaders headers = new HttpHeaders();
        String xRapidApiHost = "seeking-alpha.p.rapidapi.comseeking-alpha.p.rapidapi.com";
        headers.set("x-rapidapi-host", xRapidApiHost);
        headers.set("x-rapidapi-key", apiKey);

        ResponseEntity<String> response = restTemplate.exchange(hostUrl +
                "?symbol="+ticker.toLowerCase()
        + "data_type=revenues&period_type=quarterly",HttpMethod.GET, new HttpEntity<>(headers), String.class);

   }
}
