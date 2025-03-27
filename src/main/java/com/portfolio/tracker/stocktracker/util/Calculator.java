package com.portfolio.tracker.stocktracker.util;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

    public static double calculateSimpleMovingAverage(Queue<Double> prices) {
        return prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public static double[] calculateRSI(double[] closePrices, int period) {
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
    }}
