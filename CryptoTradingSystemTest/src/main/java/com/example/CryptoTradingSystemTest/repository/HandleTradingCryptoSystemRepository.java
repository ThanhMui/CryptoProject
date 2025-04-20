package com.example.CryptoTradingSystemTest.repository;

import com.example.CryptoTradingSystemTest.model.BestPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandleTradingCryptoSystemRepository extends CrudRepository<BestPrice, Long> {
    BestPrice findFirstBySymbolOrderByCreatedDateDesc(String symbol);
    List<BestPrice> findAllByOrderByCreatedDateDesc();

}
