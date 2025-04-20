package com.example.CryptoTradingSystemTest.common;


import com.example.CryptoTradingSystemTest.model.Huo;
import com.example.CryptoTradingSystemTest.model.ModelCommon;

import java.util.Comparator;

public class SortBySymbolHuo implements Comparator<Huo> {
    public int compare(Huo obj1, Huo obj2) {
        return obj1.getSymbol().compareTo(obj2.getSymbol());
    }
}
