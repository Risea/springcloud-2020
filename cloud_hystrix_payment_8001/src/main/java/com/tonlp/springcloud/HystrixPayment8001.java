package com.tonlp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableCircuitBreaker           // 服务降级或异常
@SpringBootApplication
@EnableEurekaClient
public class HystrixPayment8001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixPayment8001.class, args);
    }

}
