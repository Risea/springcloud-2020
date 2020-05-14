package com.tonlp.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.tonlp.sc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrderApp8888 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp8888.class, args);
    }

}
