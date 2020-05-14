package com.tonlp.springcloud.client;

import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springcloud.entities.PaymentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentClient {

    /**
     * Feign 调用 必须明确定义参数，PathVariable("id")
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    CommonResult<PaymentEntity> get(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    CommonResult<PaymentEntity> create(@RequestBody PaymentEntity paymentEntity);

    @GetMapping("/payment/get/timeout/{id}")
    CommonResult<PaymentEntity> getTimeout(@PathVariable("id") Long id);

}
