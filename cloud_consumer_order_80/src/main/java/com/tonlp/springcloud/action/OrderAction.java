package com.tonlp.springcloud.action;

import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springcloud.entities.PaymentEntity;
import com.tonlp.springcloud.mylb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequestMapping("/order")
@RestController
@Slf4j
public class OrderAction {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    // 集群用服务名访问得话， 需要开启负载均衡LoadBalanced才知道服务地址
    static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult<PaymentEntity> create(PaymentEntity paymentEntity){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", paymentEntity, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/get2/{id}")
    public CommonResult getEntity(@PathVariable Long id){
        ResponseEntity<CommonResult> re = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/"+id, CommonResult.class);
        if(re.getStatusCode().is2xxSuccessful()){
            log.info(re.getHeaders().toString());
            return re.getBody();
        }else{
            return new CommonResult<>(404, "为查询到数据");
        }
    }

    @GetMapping("/get3/{id}")
    public CommonResult<PaymentEntity> getByMyLB(@PathVariable Long id){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        // 自定义 LoadBalance 需要关闭 Template 自动注入中的 @LoadBalanced
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        System.out.println(uri);
        return restTemplate.getForObject(uri.toString() + "/payment/get/"+id, CommonResult.class);
    }
}
