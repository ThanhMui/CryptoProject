package com.example.CryptoTradingSystemTest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TradingTransHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String phoneNumber;
    private String transType;
    private String sourceFrom;
    private double price;
    private int amount;
    private String typeCoin;
    private LocalDateTime createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getTypeCoin() {
        return typeCoin;
    }

    public void setTypeCoin(String typeCoin) {
        this.typeCoin = typeCoin;
    }

    @Override
    public String toString() {
        return "TradingTransHistory{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", transType='" + transType + '\'' +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", typeCoin='" + typeCoin + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
