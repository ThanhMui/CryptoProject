package com.example.CryptoTradingSystemTest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class InfoUser {
    @Id
    private String phoneNumber;
    private double walletBalance;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

}
