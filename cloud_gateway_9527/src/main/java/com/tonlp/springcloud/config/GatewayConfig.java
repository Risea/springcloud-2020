package com.tonlp.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // 配置路由
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("dvision", r -> r.path("/dvision")
                        .uri("http://47.90.56.205:18888"))
                .route("demo", r -> r.path("/demo")
                        .uri("http://47.90.56.205:19999"))
                .build();
    }

}
