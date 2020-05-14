package com.tonlp.sc.service;

import com.tonlp.sc.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public void decrease(Long userId, BigDecimal money){
        accountMapper.updateMoneyByUserId(userId, money);
    }

}
