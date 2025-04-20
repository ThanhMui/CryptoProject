package com.example.CryptoTradingSystemTest.service;


import com.example.CryptoTradingSystemTest.model.TradingTransHistory;
import com.example.CryptoTradingSystemTest.repository.TradingTransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingTransactionHistoryServiceImpl implements TradingTransactionHistoryService {

    private final TradingTransactionHistoryRepository tradingTransactionHistoryRepository;

    @Autowired
    public TradingTransactionHistoryServiceImpl(TradingTransactionHistoryRepository tradingTransactionHistoryRepository) {
        this.tradingTransactionHistoryRepository = tradingTransactionHistoryRepository;
    }

    @Override
    public List<TradingTransHistory> getUserTradingHistory(String phoneNumber) {
        return tradingTransactionHistoryRepository.findTransHistoryByPhoneNumber(phoneNumber);
    }
}
