package com.portfolio.tracker.stocktracker.controller;


import com.portfolio.tracker.stocktracker.dto.DailyPricesDTO;
import com.portfolio.tracker.stocktracker.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticker")
public class TickerController {

    private TickerService tickerService;

    @Autowired
    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<List<DailyPricesDTO>> getDecadeData(@PathVariable("ticker") String ticker) {
        List<DailyPricesDTO> prices = tickerService.getDecadeData(ticker);
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/{ticker}/seed")
    public ResponseEntity<String> seedData(@PathVariable("ticker") String ticker) {
        tickerService.seedDatabase(ticker);
        return ResponseEntity.ok(ticker);
    }

    @GetMapping("/{ticker}/fundamentals")
    public ResponseEntity<String> seedFundamentals(@PathVariable("ticker") String ticker) {
        tickerService.seedFundamentals(ticker);
        var result = tickerService.getAnnualStatements(ticker);
        System.out.println(result);
        return ResponseEntity.ok(ticker);
    }




}
