package com.example.CryptoTradingSystemTest.service;


import com.example.CryptoTradingSystemTest.model.TradingTransHistory;

import java.util.List;

public interface TradingTransactionHistoryService {
    List<TradingTransHistory> getUserTradingHistory(String phoneNumber);
}
