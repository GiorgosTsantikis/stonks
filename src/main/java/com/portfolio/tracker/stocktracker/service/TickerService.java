package com.portfolio.tracker.stocktracker.service;

import com.portfolio.tracker.stocktracker.dto.AnnualStatementDTO;
import com.portfolio.tracker.stocktracker.entity.Stock;
import com.portfolio.tracker.stocktracker.dto.DailyPricesDTO;
import com.portfolio.tracker.stocktracker.repository.StockRepository;
import com.portfolio.tracker.stocktracker.rest.RestExchange;
import com.portfolio.tracker.stocktracker.util.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static com.portfolio.tracker.stocktracker.util.Calculator.calculateSimpleMovingAverage;

@Service
public class TickerService {

    @Value("${tiingo.api.token}")
    private String apiKey;

    @Value("${tiingo.host.url}")
    private String hostUrl;

    @Value("${polygon.apiKey}")
    private String polygonKey;

    @Value("${polygon.host.url}")
    private String polygonHostUrl;

    private StockRepository stockRepository;

    @Autowired
    public TickerService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<DailyPricesDTO> getDecadeData(String ticker){
        List<DailyPricesDTO> prices = RestExchange.getDecadeData(ticker,apiKey,hostUrl);
        double totalDividends = 0;
        for(DailyPricesDTO price : prices){
            totalDividends += price.getDivCash();
        }
        System.out.println("total dividends: " + totalDividends);
        return prices;
    }

    public void seedDatabase(String ticker) {
        List<DailyPricesDTO> prices = RestExchange.getDecadeData(ticker,apiKey,hostUrl);
        double[] closePrices =  prices.stream().mapToDouble(x->x.getClose()).toArray();
        double[] rsiVals = Calculator.calculateRSI(closePrices,14);
        Queue<Double> twoHundred = new LinkedList<>();
        Queue<Double> oneFifty = new LinkedList<>();
        Queue<Double> hundred = new LinkedList<>();
        Queue<Double> fifty = new LinkedList<>();
        Queue<Double> twenty = new LinkedList<>();

        Stock stock = null;
        DailyPricesDTO price = null;
        for(int i = 0; i < prices.size(); i++){
            twenty.add(prices.get(i).getClose());
            fifty.add(prices.get(i).getClose());
            hundred.add(prices.get(i).getClose());
            oneFifty.add(prices.get(i).getClose());
            twoHundred.add(prices.get(i).getClose());
            stock = new Stock();
            price = prices.get(i);
            stock.setSymbol(ticker);
            stock.setClose(BigDecimal.valueOf(price.getClose()));
            stock.setOpen(BigDecimal.valueOf(price.getOpen()));
            stock.setDivCash(BigDecimal.valueOf(price.getDivCash()));
            stock.setRsi(BigDecimal.valueOf(rsiVals[i]));
            stock.setTimestamp(Instant.parse(price.getTimestamp()));
            stock.setVolume(price.getVolume());
            if(i >= 19){
                stock.setSma20(BigDecimal.valueOf(calculateSimpleMovingAverage(twenty)));
                twenty.poll();
            }
            if(i >= 49){
                stock.setSma50(BigDecimal.valueOf(calculateSimpleMovingAverage(fifty)));
                fifty.poll();
            }
            if(i >= 99){
                stock.setSma100(BigDecimal.valueOf(calculateSimpleMovingAverage(hundred)));
                hundred.poll();
            }
            if(i >= 149){
                stock.setSma150(BigDecimal.valueOf(calculateSimpleMovingAverage(oneFifty)));
                oneFifty.poll();
            }
            if(i >= 199){
                stock.setSma200(BigDecimal.valueOf(calculateSimpleMovingAverage(twoHundred)));
                twoHundred.poll();
            }
            stockRepository.save(stock);
        }
    }

    public List<AnnualStatementDTO> getAnnualStatements(String ticker) {
        return RestExchange.getFundamentals(ticker,polygonKey,polygonHostUrl,"2013-01-01");
    }




    public void seedFundamentals(String ticker) {
    }
}
