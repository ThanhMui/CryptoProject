package com.example.CryptoTradingSystemTest.repository;

import com.example.CryptoTradingSystemTest.model.InfoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWalletBalanceRepository extends CrudRepository<InfoUser, String> {
    InfoUser findInfoUserByPhoneNumber(String phoneNumber);
}
