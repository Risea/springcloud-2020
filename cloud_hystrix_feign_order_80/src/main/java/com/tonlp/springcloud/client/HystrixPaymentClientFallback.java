package com.tonlp.springcloud.client;

import org.springframework.stereotype.Component;

@Component
public class HystrixPaymentClientFallback implements HystrixPaymentClient {
    @Override
    public String payment_ok(Long id) {
        return "对某个微服务统一异常拦截处理，method: payment_ok, id: "+id;
    }

    @Override
    public String payment_timeout(Long id) {
        return "对某个微服务统一异常拦截处理，method: payment_timeout, id: "+id;
    }


}
