package com.example.CryptoTradingSystemTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelCommon {
    private String symbol;
    @JsonProperty("bid")
    private double bidPrice;
    @JsonProperty("ask")
    private double askPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
