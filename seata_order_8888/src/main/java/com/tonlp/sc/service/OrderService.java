package com.tonlp.sc.service;

import com.tonlp.sc.client.AccountService;
import com.tonlp.sc.client.StorageService;
import com.tonlp.sc.domain.ScOrder;
import com.tonlp.sc.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    /**
     * 创建订单 -- 减库存 -- 扣余额     * @param scOrder
     */
    @GlobalTransactional
    public void create(ScOrder scOrder) {
        log.info("1. 保存订单 ....");
        // 1. order保存
        orderMapper.createOrder(scOrder);
        log.info("2. 减库存...");
        // 2. 减库存
        storageService.decrease(scOrder.getProductId(), scOrder.getCount());
        log.info("3. 扣余额...");
        // 3. 扣余额
        accountService.decrease(scOrder.getUserId(), scOrder.getMoney());
        log.info("4. 更新订单状态...");
        orderMapper.update(scOrder.getId(), 1);
        log.info("5. 订单完成");
    }
}
