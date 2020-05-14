package com.tonlp.springcloud.action;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tonlp.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hystrix/payment")
@RestController
@Slf4j
public class HystrixPaymentAction {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private HystrixPaymentService hystrixPaymentService;

    @GetMapping("/ok")
    public String payment_ok2(){
        return serverPort;
    }

    @GetMapping("/ok/{id}")
    public String payment_ok(@PathVariable("id") Long id){
        String res = hystrixPaymentService.payment_ok(id);
        log.info("----- "+res);
        return res;
    }

    @GetMapping("/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Long id){
        String res = hystrixPaymentService.payment_timeout(id);
        log.info("----- "+res);
        return res;
    }


    // 服务熔断-----------------------------------------------
    // 10次访问中，失败率达到60%，就会熔断，短期内所有请求都无法访问、过后会缓步 尝试 恢复
    // 具体属性查看HystrixCommandProperties.class
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandle", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    // 失败率多少时 熔断
    })
    @GetMapping("/cb/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        if(id < 0){
            throw new RuntimeException("查询id 小于 0 ---> "+id);
        }else{
            String orderNum = IdUtil.simpleUUID();
            return Thread.currentThread().getName()+"调用成功， id: "+id+", 订单号: "+orderNum;
        }
    }

    public String paymentCircuitBreakerHandle(Long id){
        return "id 不能为负数，请稍后再试。 id --> "+id;
    }
}
