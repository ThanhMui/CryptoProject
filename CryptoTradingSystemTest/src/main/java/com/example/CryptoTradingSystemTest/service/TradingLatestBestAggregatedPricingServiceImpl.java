package com.example.CryptoTradingSystemTest.service;

import com.example.CryptoTradingSystemTest.model.BestPrice;
import com.example.CryptoTradingSystemTest.model.InfoUser;
import com.example.CryptoTradingSystemTest.model.TradingTransHistory;
import com.example.CryptoTradingSystemTest.model.TransInfo;
import com.example.CryptoTradingSystemTest.repository.HandleTradingCryptoSystemRepository;
import com.example.CryptoTradingSystemTest.repository.TradingTransactionHistoryRepository;
import com.example.CryptoTradingSystemTest.repository.TransInfoUserRepository;
import com.example.CryptoTradingSystemTest.repository.UserWalletBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradingLatestBestAggregatedPricingServiceImpl implements TradingLatestBestAggregatedPricingService {
    private final UserWalletBalanceRepository userWalletBalanceRepository;
    private final HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository;
    private final TransInfoUserRepository transInfoUserRepository;
    private final TradingTransactionHistoryRepository tradingTransactionHistoryRepository;

    @Autowired
    public TradingLatestBestAggregatedPricingServiceImpl(UserWalletBalanceRepository userWalletBalanceRepository,
                                                         HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository,
                                                         TransInfoUserRepository transInfoUserRepository,
                                                         TradingTransactionHistoryRepository tradingTransactionHistoryRepository) {
        this.userWalletBalanceRepository = userWalletBalanceRepository;
        this.handleTradingCryptoSystemRepository = handleTradingCryptoSystemRepository;
        this.transInfoUserRepository = transInfoUserRepository;
        this.tradingTransactionHistoryRepository = tradingTransactionHistoryRepository;
    }

    @Override
    public String  tradingUser(String phoneNumber, String type, int amount, String requiredCoinType) {
        List<BestPrice> bestPriceList = handleTradingCryptoSystemRepository.findAllByOrderByCreatedDateDesc();
       bestPriceList = bestPriceList.subList(0, Math.min(2, bestPriceList.size()));
       BestPrice price =   bestPriceList.stream()
               .filter(s -> s.getSymbol().equalsIgnoreCase(requiredCoinType))
               .findFirst()
               .orElse(null);
        if (price== null){
            return null;
        }
        InfoUser infoUser = userWalletBalanceRepository.findInfoUserByPhoneNumber(phoneNumber);
        TransInfo transInfo = transInfoUserRepository.findTransInfoByPhoneNumberAndType(phoneNumber, requiredCoinType);
        TradingTransHistory tradingTransHistory = new TradingTransHistory();
        double currentPrice = type.equalsIgnoreCase("BUY") ? price.getBestAskPrice() : price.getBestBidPrice();
        String fromSource = type.equalsIgnoreCase("BUY") ? price.getBestAskSource() : price.getBestBidSource();
        double totalPrice = amount * currentPrice;
        if (transInfo== null){
            transInfo = new TransInfo();
            transInfo.setType(requiredCoinType);
            transInfo.setPhoneNumber(phoneNumber);
        }
        if (type.equalsIgnoreCase("BUY")) {
            if (infoUser.getWalletBalance() < totalPrice) return "Insufficient money";
            infoUser.setWalletBalance(infoUser.getWalletBalance() - totalPrice);
            transInfo.setAmount(transInfo.getAmount() + amount);
        } else {
            if (transInfo.getAmount() < amount) return "Insufficient amount";
            transInfo.setAmount(transInfo.getAmount() - amount);
            infoUser.setWalletBalance(infoUser.getWalletBalance() + totalPrice);
        }
        tradingTransHistory.setTypeCoin(requiredCoinType);
        tradingTransHistory.setPrice(currentPrice);
        tradingTransHistory.setTransType(type);
        tradingTransHistory.setTypeCoin(requiredCoinType);
        tradingTransHistory.setPhoneNumber(phoneNumber);
        tradingTransHistory.setAmount(amount);
        tradingTransHistory.setSourceFrom(fromSource);
        tradingTransHistory.setCreatedDate(LocalDateTime.now());
        tradingTransactionHistoryRepository.save(tradingTransHistory);
        userWalletBalanceRepository.save(infoUser);
        transInfoUserRepository.save(transInfo);
        return "successful!";
    }
}
