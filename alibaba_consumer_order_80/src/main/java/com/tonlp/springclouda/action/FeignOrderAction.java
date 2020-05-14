package com.tonlp.springclouda.action;

import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springclouda.client.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order/feign")
@RestController
public class FeignOrderAction {

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return paymentClient.get(id);
    }

}
