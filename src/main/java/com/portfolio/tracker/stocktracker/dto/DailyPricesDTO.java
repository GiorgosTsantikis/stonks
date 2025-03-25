package com.portfolio.tracker.stocktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class DailyPricesDTO {

    @JsonProperty("date")
    private String date;

    private double close;
    private double high;
    private double low;
    private double open;
    private long volume;
    private double adjClose;
    private double adjHigh;
    private double adjLow;
    private double adjOpen;
    private long adjVolume;
    private double divCash;
    private double splitFactor;

    public DailyPricesDTO() {
    }

    public DailyPricesDTO(String date, double close, double high, double low, long volume, double adjHigh, double adjLow, double adjOpen, double divCash, double splitFactor, long adjVolume, double adjClose, double open) {
        this.date = date;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.adjHigh = adjHigh;
        this.adjLow = adjLow;
        this.adjOpen = adjOpen;
        this.divCash = divCash;
        this.splitFactor = splitFactor;
        this.adjVolume = adjVolume;
        this.adjClose = adjClose;
        this.open = open;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public double getDivCash() {
        return divCash;
    }

    public void setDivCash(double divCash) {
        this.divCash = divCash;
    }

    public double getSplitFactor() {
        return splitFactor;
    }

    public void setSplitFactor(double splitFactor) {
        this.splitFactor = splitFactor;
    }

    public long getAdjVolume() {
        return adjVolume;
    }

    public void setAdjVolume(long adjVolume) {
        this.adjVolume = adjVolume;
    }

    public double getAdjLow() {
        return adjLow;
    }

    public void setAdjLow(double adjLow) {
        this.adjLow = adjLow;
    }

    public double getAdjOpen() {
        return adjOpen;
    }

    public void setAdjOpen(double adjOpen) {
        this.adjOpen = adjOpen;
    }

    public double getAdjHigh() {
        return adjHigh;
    }

    public void setAdjHigh(double adjHigh) {
        this.adjHigh = adjHigh;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getTimestamp() {
        return date;
    }

    public void setTimestamp(String timestamp) {
        this.date = timestamp;
    }
}
