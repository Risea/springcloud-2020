package com.tonlp.springcloud.action;

import com.tonlp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/consul/order")
@RestController
@Slf4j
public class ConsulOrderAction {

    private static String PAYMENT_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public CommonResult<Object> get(){
        return restTemplate.getForObject(PAYMENT_URL + "/consul/payment/", CommonResult.class);
    }

}
