package com.example.CryptoTradingSystemTest.component;


import com.example.CryptoTradingSystemTest.api.GetDataFromApi;
import com.example.CryptoTradingSystemTest.common.SortBySymbol;
import com.example.CryptoTradingSystemTest.common.SortBySymbolHuo;
import com.example.CryptoTradingSystemTest.model.BestPrice;
import com.example.CryptoTradingSystemTest.model.Huo;
import com.example.CryptoTradingSystemTest.model.ModelCommon;
import com.example.CryptoTradingSystemTest.repository.HandleTradingCryptoSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HandleTradingCryptoSystemComponent {
    private final HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository;

    @Autowired
    public HandleTradingCryptoSystemComponent(HandleTradingCryptoSystemRepository handleTradingCryptoSystemRepository) {
        this.handleTradingCryptoSystemRepository = handleTradingCryptoSystemRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void saveBestPricing() {
        GetDataFromApi getDataFromApi = new GetDataFromApi();
        List<ModelCommon> dataBinanceList = getDataFromApi.getDataApiBinance();
        List<Huo> dataHoubiList = getDataFromApi.getDataApiHuobi();
        SortBySymbol sortBySymbol = new SortBySymbol();
        SortBySymbolHuo sortBySymbolHuo = new SortBySymbolHuo();

        dataBinanceList.sort(sortBySymbol);
        dataHoubiList.sort(sortBySymbolHuo);
        ModelCommon dataBinance = null;
        Huo dataHoubi = null;
        for (int i = 0; i < dataBinanceList.size(); i++) {
            BestPrice bestPrice = new BestPrice();
            dataBinance = dataBinanceList.get(i);
            dataHoubi = dataHoubiList.get(i);
            if (dataBinance.getBidPrice() < dataHoubi.getBid()) {
                bestPrice.setBestBidPrice(dataHoubi.getBid());
                bestPrice.setBestBidSource("Huobi");

            } else {
                bestPrice.setBestBidPrice(dataBinance.getBidPrice());
                bestPrice.setBestBidSource("Binance");

            }
            if (dataBinance.getAskPrice() < dataHoubi.getAsk()) {
                bestPrice.setBestAskPrice(dataBinance.getAskPrice());
                bestPrice.setBestAskSource("Binance");

            } else {
                bestPrice.setBestAskPrice(dataHoubi.getAsk());
                bestPrice.setBestAskSource("Huobi");

            }
            bestPrice.setSymbol(dataHoubi.getSymbol().toLowerCase());
            bestPrice.setCreatedDate(LocalDateTime.now());
            handleTradingCryptoSystemRepository.save(bestPrice);
        }
    }

}
