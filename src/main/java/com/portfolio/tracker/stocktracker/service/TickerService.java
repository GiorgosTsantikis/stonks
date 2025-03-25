package com.portfolio.tracker.stocktracker.service;

import com.portfolio.tracker.stocktracker.entity.Stock;
import com.portfolio.tracker.stocktracker.dto.DailyPricesDTO;
import com.portfolio.tracker.stocktracker.repository.StockRepository;
import com.portfolio.tracker.stocktracker.rest.RestExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Service
public class TickerService {

    @Value("${tiingo.api.token}")
    private String apiKey;

    @Value("${tiingo.host.url}")
    private String hostUrl;

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
        double[] rsiVals = calculateRSI(closePrices,14);
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

    public double calculateSimpleMovingAverage(Queue<Double> prices) {
        return prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public  double[] calculateRSI(double[] closePrices, int period) {
        if (closePrices.length < period) {
            throw new IllegalArgumentException("Not enough data to calculate RSI");
        }

        double[] rsi = new double[closePrices.length];
        Queue<Double> gains = new LinkedList<>();
        Queue<Double> losses = new LinkedList<>();
        double avgGain = 0, avgLoss = 0;

        // Calculate initial average gain/loss over the first 'period' days
        for (int i = 1; i <= period; i++) {
            double change = closePrices[i] - closePrices[i - 1];
            if (change > 0) {
                gains.add(change);
                avgGain += change;
                losses.add(0.0);
            } else {
                losses.add(-change);
                avgLoss += -change;
                gains.add(0.0);
            }
        }
        avgGain /= period;
        avgLoss /= period;

        // Calculate RSI for the first valid index
        rsi[period] = computeRSI(avgGain, avgLoss);

        // Process the rest of the prices
        for (int i = period + 1; i < closePrices.length; i++) {
            double change = closePrices[i] - closePrices[i - 1];

            double gain = Math.max(change, 0);
            double loss = Math.max(-change, 0);

            gains.add(gain);
            losses.add(loss);

            avgGain = (avgGain * (period - 1) + gain) / period;
            avgLoss = (avgLoss * (period - 1) + loss) / period;

            rsi[i] = computeRSI(avgGain, avgLoss);

            // Remove oldest value to maintain queue size
            gains.poll();
            losses.poll();
        }

        return rsi;
    }

    private static double computeRSI(double avgGain, double avgLoss) {
        if (avgLoss == 0) return 100;
        double rs = avgGain / avgLoss;
        return 100 - (100 / (1 + rs));
    }


    public void seedFundamentals(String ticker) {
    }
}
