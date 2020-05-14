package com.tonlp.springcloud.mylb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 从 services 中选出 一个服务实例
     * @param services
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> services);

}
