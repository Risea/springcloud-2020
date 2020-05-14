package com.tonlp.springcloud.service;

import com.tonlp.springcloud.dao.PaymentMapper;
import com.tonlp.springcloud.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public PaymentEntity findById(Long id){
        return paymentMapper.findById(id);
    }

    public int save(PaymentEntity paymentEntity) {
        return paymentMapper.save(paymentEntity);
    }
}
