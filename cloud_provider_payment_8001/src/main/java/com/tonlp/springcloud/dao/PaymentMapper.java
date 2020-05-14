package com.tonlp.springcloud.dao;

import com.tonlp.springcloud.entities.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    PaymentEntity findById(Long id);

    int save(PaymentEntity paymentEntity);

}
