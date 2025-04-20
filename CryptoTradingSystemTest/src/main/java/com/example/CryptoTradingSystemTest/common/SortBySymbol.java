package com.example.CryptoTradingSystemTest.common;


import com.example.CryptoTradingSystemTest.model.ModelCommon;

import java.util.Comparator;

public class SortBySymbol implements Comparator<ModelCommon> {
    public int compare(ModelCommon obj1, ModelCommon obj2) {
        return obj1.getSymbol().compareTo(obj2.getSymbol());
    }
}
