package com.tonlp.springcloud.action;

import com.tonlp.springcloud.client.PaymentClient;
import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springcloud.entities.PaymentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@Slf4j
public class OrderAction {

    @Autowired
    private PaymentClient paymentClient;

    @PostMapping("/create")
    public CommonResult<PaymentEntity> create(PaymentEntity paymentEntity){
        log.info("feign call --> "+paymentEntity.toString());
        return paymentClient.create(paymentEntity);
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable Long id){
        log.info("feign call --> "+id.toString());
        return paymentClient.get(id);
    }


    @GetMapping("/get/timeout/{id}")
    public CommonResult<PaymentEntity> getTimeout(@PathVariable Long id){
        log.info("feign timeout call --> "+id.toString());
        return paymentClient.getTimeout(id);
    }

}
