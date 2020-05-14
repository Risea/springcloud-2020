package com.tonlp.springcloud.action;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tonlp.springcloud.client.HystrixPaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DefaultProperties(defaultFallback = "defaultOrderActionHandle")
@RequestMapping("/order")
@RestController
public class HystrixOrderAction {

    @Autowired
    private HystrixPaymentClient hystrixPaymentClient;

    @HystrixCommand
    @GetMapping("/ok/{id}")
    public String payment_ok(@PathVariable("id") Long id){
        //int age = 10/0;
        String res = hystrixPaymentClient.payment_ok(id);
        System.out.println(res);
        return res;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Long id){
        return hystrixPaymentClient.payment_timeout(id);
    }

    public String paymentTimeoutHandle(@PathVariable("id") Long id){
        return "timeout客户端服务降级 ..."+id;
    }

    public String defaultOrderActionHandle(){
        return "global 客户端服务降级 ...";
    }
}
