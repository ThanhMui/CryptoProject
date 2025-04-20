package com.example.CryptoTradingSystemTest.controller;


import com.example.CryptoTradingSystemTest.model.BestPrice;
import com.example.CryptoTradingSystemTest.model.TradingTransHistory;
import com.example.CryptoTradingSystemTest.service.HandleTradingCryptoSystemService;
import com.example.CryptoTradingSystemTest.service.TradingLatestBestAggregatedPricingService;
import com.example.CryptoTradingSystemTest.service.TradingTransactionHistoryService;
import com.example.CryptoTradingSystemTest.service.UserCurrenciesWalletBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HandleTradingCryptoSystemController {

    private final HandleTradingCryptoSystemService handleTradingCryptoSystemService;
    private final TradingTransactionHistoryService tradingTransactionHistoryService;
    private final UserCurrenciesWalletBalance userCurrenciesWalletBalance;
    private final TradingLatestBestAggregatedPricingService tradingLatestBestAggregatedPricingService;

    @Autowired
    public HandleTradingCryptoSystemController(HandleTradingCryptoSystemService handleTradingCryptoSystemService,
                                               TradingTransactionHistoryService tradingTransactionHistoryService,
                                               UserCurrenciesWalletBalance userCurrenciesWalletBalance,
                                               TradingLatestBestAggregatedPricingService tradingLatestBestAggregatedPricingService) {
        this.handleTradingCryptoSystemService = handleTradingCryptoSystemService;
        this.tradingTransactionHistoryService = tradingTransactionHistoryService;
        this.userCurrenciesWalletBalance = userCurrenciesWalletBalance;
        this.tradingLatestBestAggregatedPricingService = tradingLatestBestAggregatedPricingService;
    }

    @GetMapping("/latest-best-aggregated-price")
    public List<BestPrice> getLatestBestAggregatedPrice() {
        return handleTradingCryptoSystemService.getLatestBestAggregatedPrice();
    }

    @PostMapping("/trade")
    public String tradingBasedOnLatestBestAggregatedPrice(@RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam String type, @RequestParam int amount, @RequestParam String requiredCoinType) {
        return tradingLatestBestAggregatedPricingService.tradingUser(phoneNumber, type, amount, requiredCoinType);

    }

    @GetMapping("/currencies-wallet-balance")
    public double getCurrenciesWalletBalanceOfUser(@RequestParam String phoneNumber) {
        return userCurrenciesWalletBalance.getCurrenciesWalletBalanceOfUser(phoneNumber);
    }

    @GetMapping("/user-trading-history")
    public List<TradingTransHistory> getUserTradingHistory(@RequestParam String phoneNumber) {
        return tradingTransactionHistoryService.getUserTradingHistory(phoneNumber);
    }

    @PostMapping("/login")
    public void login(@RequestParam(name = "phoneNumber") String phoneNumber) {
        userCurrenciesWalletBalance.login(phoneNumber);
    }
}
