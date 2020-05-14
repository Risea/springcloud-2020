package com.tonlp.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.tonlp.sc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StorageApp8877 {

    public static void main(String[] args) {
        SpringApplication.run(StorageApp8877.class, args);
    }

}
