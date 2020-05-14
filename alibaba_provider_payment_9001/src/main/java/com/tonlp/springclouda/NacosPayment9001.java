package com.tonlp.springclouda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosPayment9001 {

    public static void main(String[] args) {
        SpringApplication.run(NacosPayment9001.class, args);
    }

}
