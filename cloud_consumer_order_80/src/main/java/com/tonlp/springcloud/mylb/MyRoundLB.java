package com.tonlp.springcloud.mylb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyRoundLB implements LoadBalancer {

    // 原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 计算总次数
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            // next 自增 1
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
            // 交换 当前值与下一个值 、因为多线程下 当前值可能已经被改变了。
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("************** next -------> "+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> services) {
        int idx = getAndIncrement() % services.size();
        return services.get(idx);
    }
}
