package com.tonlp.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.tonlp.sc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AccountApp8866 {

    public static void main(String[] args) {
        SpringApplication.run(AccountApp8866.class, args);
    }

}
