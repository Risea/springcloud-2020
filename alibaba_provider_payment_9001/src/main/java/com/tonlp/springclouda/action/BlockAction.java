package com.tonlp.springclouda.action;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springclouda.exhandler.MyBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/block")
@RestController
public class BlockAction {

    @GetMapping("/byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "by url");
    }

    @SentinelResource(value = "byCustomer1", blockHandlerClass = MyBlockHandler.class, blockHandler = "byCustomer1Handle")
    @GetMapping("/byCustomer1")
    public CommonResult byCustomer1(){
        return new CommonResult(200, "by Customer 1");
    }

    @SentinelResource(value = "byCustomer2", blockHandlerClass = MyBlockHandler.class, blockHandler = "byCustomer2Handle")
    @GetMapping("/byCustomer2")
    public CommonResult byCustomer2(){
        return new CommonResult(200, "by Customer 2");
    }

}
