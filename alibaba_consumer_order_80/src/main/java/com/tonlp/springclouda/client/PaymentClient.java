package com.tonlp.springclouda.client;

import com.tonlp.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentClientImpl.class)
public interface PaymentClient {

    @GetMapping("/payment/get/{id}")
    public CommonResult<String> get(@PathVariable("id") Long id);

}
