package com.tonlp.springcloud.action;

import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springcloud.entities.PaymentEntity;
import com.tonlp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentAction {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/get")
    public CommonResult<String> get(){
        return new CommonResult<>(200, serverPort+" 查询成功 --> O(∩_∩)O", serverPort);
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable Long id){
        log.info(serverPort+" -->  id -->  "+id);
        PaymentEntity paymentEntity = paymentService.findById(id);
        return new CommonResult<>(200, serverPort+" 查询成功 --> O(∩_∩)O", paymentEntity);
    }

    @PostMapping("/create")
    public CommonResult<PaymentEntity> create(@RequestBody PaymentEntity paymentEntity){
        int res = paymentService.save(paymentEntity);
        log.info(serverPort+" -->  "+paymentEntity);
        if(res > 0){
            return new CommonResult<>(200, serverPort+" 保存成功", paymentEntity);
        }else{
            return new CommonResult<>(500, serverPort+" 保存失败", paymentEntity);
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service: services) {
            log.info(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance ins: instances) {
            log.info(ins.getInstanceId()+"--> "+ins.getHost()+":"+ins.getPort()+ins.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/get/timeout/{id}")
    public CommonResult<PaymentEntity> getTimeout(@PathVariable Long id) throws InterruptedException {
        // 测试超时 3s
        Thread.sleep(3000);
        log.info(serverPort+" -->  id -->  "+id);
        PaymentEntity paymentEntity = paymentService.findById(id);
        return new CommonResult<>(200, serverPort+" 查询成功 --> "+id, paymentEntity);
    }
}
