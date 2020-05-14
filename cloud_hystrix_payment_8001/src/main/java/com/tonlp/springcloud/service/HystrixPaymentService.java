package com.tonlp.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class HystrixPaymentService {

    public String payment_ok(Long id){
        return "payment_ok 当前线程："+Thread.currentThread().getName()+", id: "+id+" O(∩_∩)O";
    }

    /**
     * paymentTimeoutHandle 作为 此方法异常或超时的 兜底处理方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String payment_timeout(Long id){
        Integer timeout = 3;
        //int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "payment_timeout 当前线程："+Thread.currentThread().getName()+", id: "+id+" ┭┮﹏┭┮, 耗时(秒): "+timeout;
    }

    public String paymentTimeoutHandle(Long id){
        return "paymentTimeoutHandle 当前线程："+Thread.currentThread().getName()+", id: "+id+" 服务降级";
    }


}
