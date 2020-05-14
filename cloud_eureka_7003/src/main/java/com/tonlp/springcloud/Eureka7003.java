package com.tonlp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eureka7003 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7003.class, args);
    }
}
