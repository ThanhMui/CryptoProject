package com.example.CryptoTradingSystemTest.repository;

import com.example.CryptoTradingSystemTest.model.TradingTransHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingTransactionHistoryRepository extends CrudRepository<TradingTransHistory, Long> {

    List<TradingTransHistory> findTransHistoryByPhoneNumber(String phoneNumber);

}
