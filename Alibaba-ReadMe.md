### SpringCloud-Alibaba
#### Nacos 服务注册及配置中心(Naming+Config+Server)
https://nacos.io/zh-cn/docs/quick-start-docker.html
https://github.com/nacos-group/nacos-docker/blob/master/README_ZH.md
Nacos自带Ribbon负载均衡
Nacos支持AP与CP切换
C: 数据一致性，同一时间看到的数据是一致的
A: 高可用、所有的请求都要有响应
P: 分布式
切换模式: curl -X PUT http://localhost:8848/nacos/v1/operator/switches?entry=serverMode&value=CP


Nacos配置中心 Data ID 规则
${prefix}-${spring.profile.active}.${file-extension}
- prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
- spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
- file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。


Namespace+Group+Service+DataID
Namespace隔离环境，开发、测试、生产
Group可以将不同的微服务划分到一个组里

集群 https://nacos.io/zh-cn/docs/quick-start-docker.html
docker run -d -p 8848:8848 --name nacos 
-e NACOS_SERVERS="ip1:8848 ip2:8848" 
-e NACOS_SERVER_IP=47.90.56.205 
-e SPRING_DATASOURCE_PLATFORM=mysql 
-e MYSQL_SERVICE_HOST=mysql-ip 
-e MYSQL_SERVICE_DB_NAME=nacos 
-e MYSQL_SERVICE_USER=root 
-e MYSQL_SERVICE_PASSWORD=4FPAt2AVpFLhRSB3 
--restart=always 
nacos/nacos-server



#### Sentinel
单机版启动
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar
#### 流控
QPS: 每秒访问量
线程数: --
流控规则:
直接: 
关联: /A 关联 /B    /B大量访问被限流后、会导致/A也被限流
链路:
流控效果:
快速失败: --
Warm UP: 预热，根据冷加载因子(默认:3),从(阈值/因子) ---经过预热时长--- 阈值
排队等待: 匀速排队处理，每秒处理几个。
#### 熔断、降级
https://sentinelguard.io/zh-cn/docs/circuit-breaking.html
RT: 平均响应时间/s 
异常比例: 异常数量占比
异常数量: 按分钟级别统计、时间窗口设置>=60s

#### 热点key
参数限制


@SentinelResource()
value 定义资源名             自定义资源后、只能用自定义的资源名，url匹配不经过blockHandler
blockHandlerClass 异常兜底类
exceptionsToIgnore 忽略异常
blockHandler 配置违规兜底方法
fallback 运行异常兜底方法


限流配置与异常兜底  优先限流

#### sentinel 持久化配置
持久化配置后、Sentinel端修改不会持久化到nacos
sentinel设置dashboard的规则推送
##### 流控配置
resource 资源名，资源名是限流规则的作用对象   
count 限流阈值   
grade 限流阈值类型，
QPS 或线程数模式QPS 模式 
limitApp 流控针对的调用来源 default，代表不区分调用来源 
strategy 判断的根据是资源自身，还是根据其它关联资源 (refResource)，还是根据链路入口 根据资源本身 
controlBehavior 流控效果（直接拒绝 / 排队等待 / 慢启动模式） 直接拒绝

##### 降级配置
resource 资源名，即限流规则的作用对象   
count 阈值   
grade 降级模式，根据 RT 降级还是根据异常比例降级 RT 
timeWindow 降级的时间，单位为 s




#### Seata安装配置
1. 备份file.conf
2. 修改file.conf 自定义事务名称，事务日志存储模式改为数据库存储，数据库链接信息
   - service模块
   - store模块
   建库建表 conf/db_store.sql
3. 备份registry.conf
4. 修改nacos配置