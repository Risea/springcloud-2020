package com.tonlp.springclouda.client;

import com.tonlp.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientImpl implements PaymentClient{
    @Override
    public CommonResult<String> get(Long id) {
        return new CommonResult<>(500, "兜底方法-->paymentClientImpl ");
    }
}
