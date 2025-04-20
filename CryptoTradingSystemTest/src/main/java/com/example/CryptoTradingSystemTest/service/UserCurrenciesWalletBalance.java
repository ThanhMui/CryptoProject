package com.example.CryptoTradingSystemTest.service;

public interface UserCurrenciesWalletBalance {
    double getCurrenciesWalletBalanceOfUser(String phoneNumber);
    void login(String phoneNumber);

}
