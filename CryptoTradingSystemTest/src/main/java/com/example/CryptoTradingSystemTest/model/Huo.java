package com.example.CryptoTradingSystemTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Huo {
    private String symbol;
    private double bid;
    private double ask;

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
