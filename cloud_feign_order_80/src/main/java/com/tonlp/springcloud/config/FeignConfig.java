package com.tonlp.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * Feign开启全部日志
     * @return
     */
    @Bean
    public Logger.Level getFeignLog(){
        return Logger.Level.FULL;
    }
}
