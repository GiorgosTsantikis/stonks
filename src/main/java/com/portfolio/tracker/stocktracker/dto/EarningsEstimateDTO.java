package com.portfolio.tracker.stocktracker.dto;

public class EarningsEstimateDTO {

    private String symbol;
    private String year;
    private int quarter;
    private long actual;
    private long consensus;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public long getActual() {
        return actual;
    }

    public void setActual(long actual) {
        this.actual = actual;
    }

    public long getConsensus() {
        return consensus;
    }

    public void setConsensus(long consensus) {
        this.consensus = consensus;
    }
}
