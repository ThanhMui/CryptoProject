package com.example.CryptoTradingSystemTest.repository;

import com.example.CryptoTradingSystemTest.model.TransInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransInfoUserRepository extends CrudRepository<TransInfo, String> {
    TransInfo findTransInfoByPhoneNumberAndType(String phoneNumber, String type);
}
