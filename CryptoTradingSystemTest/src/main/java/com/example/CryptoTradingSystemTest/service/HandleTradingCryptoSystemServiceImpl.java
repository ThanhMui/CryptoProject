package com.example.CryptoTradingSystemTest.service;



import com.example.CryptoTradingSystemTest.model.BestPrice;
import com.example.CryptoTradingSystemTest.repository.HandleTradingCryptoSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandleTradingCryptoSystemServiceImpl implements HandleTradingCryptoSystemService {

    private final HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository;

    @Autowired
    public HandleTradingCryptoSystemServiceImpl(HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository) {
        this.handleTradingCryptoSystemRepository = handleTradingCryptoSystemRepository;
    }

    @Override
    public List<BestPrice> getLatestBestAggregatedPrice() {
        List<BestPrice> bestPriceList = handleTradingCryptoSystemRepository.findAllByOrderByCreatedDateDesc();
        return bestPriceList.subList(0, Math.min(2, bestPriceList.size()));
    }
}
