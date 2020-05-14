package com.tonlp.springclouda.action;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tonlp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentAction {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<String> get(@PathVariable("id") Long id){
        String uuid = IdUtil.simpleUUID();
        log.info("********** {}", id);
        return new CommonResult<>(200, "服务端口: "+serverPort+",请求ID: "+id, uuid);
    }

    @GetMapping("/getTimeout")
    public String getTimeout(){
        log.info("----------- aaa");
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout : "+IdUtil.simpleUUID();
    }

    @GetMapping("/RT")
    public String RT(){
        log.info("----------- aaa");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "RT : "+IdUtil.simpleUUID();
    }

    @SentinelResource(value = "hotKey", blockHandler = "deal_hotkey")
    @GetMapping("/hotKey")
    public String hotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2){
        log.info("----------------hotkey------------{}--{}", p1, p2);
        return "----hotKey --> p1 "+p1+"   p2 "+p2;
    }


    public String deal_hotkey(String p1, String p2, BlockException ex){
        return "----deal_hotkey --> p1 "+p1+"   p2 "+p2+"      ex: "+ex.getMessage();
    }

}
