package com.tonlp.springclouda.action;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tonlp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderAction {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${servier-url.nacos-payment-server}")
    private String serverUrl;

    @Value("${config.info}")
    private String configInfo;

    // 兜底方法需public
    @SentinelResource(value = "fallback", fallback = "handlefallback", blockHandler = "handleblock",
            exceptionsToIgnore = {IllegalArgumentException.class})
    @GetMapping("/get/{id}")
    public CommonResult getData(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(serverUrl+"/payment/get/"+id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("非法参数异常...");
        }else if(id > 4){
            throw  new NullPointerException("空指针异常...");
        }
        return result;
    }

    @GetMapping("/getInfo")
    public CommonResult<String> getConfigInfo(){
        return new CommonResult<>(200, "获取Nacos配置信息", configInfo);
    }

    public CommonResult handlefallback(@PathVariable("id") Long id, Throwable e){
        return new CommonResult(400, "handle参数传递错误 --> "+id+"\t 异常信息: "+e.getMessage());
    }

    public CommonResult handleblock(@PathVariable("id") Long id, BlockException e){
        return new CommonResult(444, "block控制台限流 --> "+id+"\t 异常信息: "+e.getMessage());
    }

}
