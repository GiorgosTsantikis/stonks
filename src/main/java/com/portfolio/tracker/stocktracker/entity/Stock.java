package com.portfolio.tracker.stocktracker.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "stocks", schema = "my_database", indexes = {
        @Index(name = "symbol", columnList = "symbol"),
        @Index(name = "timestamp", columnList = "timestamp")
}, uniqueConstraints = {
        @UniqueConstraint(name = "stocks_symbol_timestamp_uindex", columnNames = {"symbol", "timestamp"})
})
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "symbol", nullable = false, length = 10)
    private String symbol;

    @Column(name = "open", nullable = false, precision = 10, scale = 2)
    private BigDecimal open;

    @Column(name = "close", nullable = false, precision = 10, scale = 2)
    private BigDecimal close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "div_cash", precision = 10, scale = 4)
    private BigDecimal divCash;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "sma200", precision = 10, scale = 2)
    private BigDecimal sma200;

    @Column(name = "sma150", precision = 10, scale = 2)
    private BigDecimal sma150;

    @Column(name = "sma100", precision = 10, scale = 2)
    private BigDecimal sma100;

    @Column(name = "sma50", precision = 10, scale = 2)
    private BigDecimal sma50;

    @Column(name = "sma20", precision = 10, scale = 2)
    private BigDecimal sma20;

    @Column(name = "rsi", precision = 10, scale = 2)
    private BigDecimal rsi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public BigDecimal getDivCash() {
        return divCash;
    }

    public void setDivCash(BigDecimal divCash) {
        this.divCash = divCash;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getSma200() {
        return sma200;
    }

    public void setSma200(BigDecimal sma200) {
        this.sma200 = sma200;
    }

    public BigDecimal getSma150() {
        return sma150;
    }

    public void setSma150(BigDecimal sma150) {
        this.sma150 = sma150;
    }

    public BigDecimal getSma100() {
        return sma100;
    }

    public void setSma100(BigDecimal sma100) {
        this.sma100 = sma100;
    }

    public BigDecimal getSma50() {
        return sma50;
    }

    public void setSma50(BigDecimal sma50) {
        this.sma50 = sma50;
    }

    public BigDecimal getSma20() {
        return sma20;
    }

    public void setSma20(BigDecimal sma20) {
        this.sma20 = sma20;
    }

    public BigDecimal getRsi() {
        return rsi;
    }

    public void setRsi(BigDecimal rsi) {
        this.rsi = rsi;
    }

}