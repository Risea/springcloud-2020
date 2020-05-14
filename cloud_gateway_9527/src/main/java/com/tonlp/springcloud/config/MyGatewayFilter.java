package com.tonlp.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
public class MyGatewayFilter implements GlobalFilter, Ordered {

    List<String> igonUrls = Arrays.asList("/create");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 当前访问路径
        String url = exchange.getRequest().getURI().getPath();
        log.info("当前访问路径："+url);
        for (String path: igonUrls) {
            if(url.contains(path)){
                log.info("igon url --> {}", url);
                return chain.filter(exchange);
            }
        }

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");
        if(StringUtils.isNotBlank(url)){
            // JWT 校验
            log.info(token);
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        //exchange.getResponse().w
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
