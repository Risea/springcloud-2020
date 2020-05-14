package com.tonlp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class Config3344 {

    public static void main(String[] args) {
        SpringApplication.run(Config3344.class, args);
    }

}
