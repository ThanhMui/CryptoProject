package com.example.CryptoTradingSystemTest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BestPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bestBidSource;
    private double bestBidPrice;
    private String symbol;
    private String bestAskSource;
    private double bestAskPrice;
    private LocalDateTime createdDate;

    public double getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(double bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(double bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public String getBestAskSource() {
        return bestAskSource;
    }

    public void setBestAskSource(String bestAskSource) {
        this.bestAskSource = bestAskSource;
    }

    public String getBestBidSource() {
        return bestBidSource;
    }

    public void setBestBidSource(String bestBidSource) {
        this.bestBidSource = bestBidSource;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
