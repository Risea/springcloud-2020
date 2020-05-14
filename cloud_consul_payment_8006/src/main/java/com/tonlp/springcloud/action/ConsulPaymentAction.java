package com.tonlp.springcloud.action;

import com.tonlp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/consul/payment")
@RestController
@Slf4j
public class ConsulPaymentAction {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/")
    public CommonResult<Object> uuid(){
        return new CommonResult<>(200, "server port: "+serverPort , UUID.randomUUID().toString());
    }

}
