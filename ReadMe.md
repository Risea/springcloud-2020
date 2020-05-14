### SpringCloud2020学习
参考链接 https://www.bilibili.com/video/BV18E411x7eT

#### 构建微服务
1. 构建module
2. 修改pom
3. 修改yml配置
3. 修改主启动类
5. 编写业务


#### Docker镜像
https://github.com/spotify/docker-maven-plugin
https://blog.csdn.net/a704397849/article/details/99656703
跨主机push镜像需要每个机器上都配置
 "insecure-registries": [
    "repos-ip:5000"
  ]
查看镜像及版本
http://repos-ip:5000/v2/_catalog
http://repos-ip:5000/v2/cloud_eureka_7002/tags/list



#### Maven 多模块打包
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <mainClass>com.tonlp.springcloud.Eureka7002</mainClass>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal><!--可以把依赖的包都打包到生成的Jar包中-->
            </goals>
        </execution>
    </executions>
</plugin>


#### 修改hosts
需要管理员权限记事本 or 另存为


#### consul 注册问题、
consul与服务不在同一网络时 
http://ip:8500/v1/agent/checks



#### 微服务调用
1. RestTemplate + Ribbon调用
2. OpenFeign + Ribbon 调用

#### 服务降级
运行异常、服务超时、服务熔断触发、线程池/信号量满了

客户端开启的话、
feign.hystrix.enable=true
@EnableHystrix
@HystrixCommand

类级别通用配置
@DefaultProperties(defaultFallback = "defaultOrderActionHandle")
defaultOrderActionHandle方法不带参数
@HystrixCommand

一般通过Feign调用微服务时做统一处理
@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE", fallback = HystrixPaymentClientFallback.class)
HystrixPaymentClientFallback实现Feign客户端接口HystrixPaymentClient


#### 服务监控
被监控服务需配置
management:
  endpoints:
    web:
      exposure:
        include: hystrix.stream
监控服务9001/hystrix页面
输入http://localhost:8001/actuator/hystrix.stream即可查看监控      


#### 网关
动态配置
spring.cloud.gateway.discovery.locator.enabled: true
uri: lb:CLOUD-PAYMENT-SERVICE

predicates: 
https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html/#gateway-request-predicates-factories
  

#### 配置中心
/{label}/{name}-{profiles}.yml   eg: localhost:3344/master/springcloud-config-dev.yml
/{name}-{profiles}.yml           eg: localhost:3344/springcloud-config-dev.yml
/{name}/{profiles}/{label}       eg: localhost:3344/springcloud-config/dev/master
label: 分支
name: 服务名
profiles: 环境
客户端手动刷新
1. 暴露actuator端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
2. Controller添加注解@RefreshScope
3. 修改后发送POST请求 http://ip:port/actuator/refresh
curl -X POST http://localhost:3355/actuator/refresh
动态刷新配置
1. 服务端整合bus-amqp、暴露bus-refresh
2. 客户端整合bus-amqp
3. 配置更新后刷新 curl -X POST http://localhost:3355/actuator/bus-refresh
4. 只更某个服务配置 curl -X POST http://localhost:3355/actuator/bus-refresh/{spring.application.name:server.port}
curl -X POST http://localhost:3355/actuator/bus-refresh/cloud-config-client:3355



#### Stream
Binder 绑定器
Channel 频道
Source/Sink 输出/输入