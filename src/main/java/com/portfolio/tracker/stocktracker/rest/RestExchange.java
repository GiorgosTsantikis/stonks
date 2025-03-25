package com.portfolio.tracker.stocktracker.rest;
import com.portfolio.tracker.stocktracker.dto.DailyPricesDTO;
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


    public static List<DailyPricesDTO> getDecadeData(String ticker,String apiKey, String hostUrl) {
        RestTemplate restTemplate = new RestTemplate();
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

    public static List<DailyPricesDTO> getFundamentals(String ticker,String apiKey, String hostUrl) {
        RestTemplate restTemplate = new RestTemplate();
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

    //"https://api.polygon.io/vX/reference/financials?ticker=MSFT&timeframe=annual&order=asc&limit=100&sort=filing_date&apiKey=UN_I2CPHNYXbuPuhvrMNS6C6tknpAdgd"
}
