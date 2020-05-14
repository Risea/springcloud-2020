package com.tonlp.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE", fallback = HystrixPaymentClientFallback.class)
public interface HystrixPaymentClient {

    @GetMapping("/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Long id);

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Long id);

}
