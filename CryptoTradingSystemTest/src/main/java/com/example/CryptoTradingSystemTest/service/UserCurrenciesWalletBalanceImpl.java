package com.example.CryptoTradingSystemTest.service;

import com.example.CryptoTradingSystemTest.model.InfoUser;
import com.example.CryptoTradingSystemTest.repository.UserWalletBalanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCurrenciesWalletBalanceImpl implements UserCurrenciesWalletBalance{
    private final UserWalletBalanceRepository userWalletBalanceRepository;

    public UserCurrenciesWalletBalanceImpl(UserWalletBalanceRepository userWalletBalanceRepository) {
        this.userWalletBalanceRepository = userWalletBalanceRepository;
    }

    @Override
    public double getCurrenciesWalletBalanceOfUser(String phoneNumber) {
        System.out.println(userWalletBalanceRepository.findInfoUserByPhoneNumber(phoneNumber));
        return userWalletBalanceRepository.findInfoUserByPhoneNumber(phoneNumber).getWalletBalance();
    }
    @Override
    public void login(String phoneNumber) {
        InfoUser user = userWalletBalanceRepository.findInfoUserByPhoneNumber(phoneNumber);
        boolean isLogin = user != null && user.getPhoneNumber().equals(phoneNumber);
        if (!isLogin){
            System.out.println("New User . Start create account user ......");
            user = new InfoUser();
            user.setWalletBalance(50000);
            user.setPhoneNumber(phoneNumber);
            userWalletBalanceRepository.save(user);
        }
        System.out.println("User logined");
    }
}
