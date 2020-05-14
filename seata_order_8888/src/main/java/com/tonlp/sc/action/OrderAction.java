package com.tonlp.sc.action;

import cn.hutool.core.util.IdUtil;
import com.tonlp.sc.domain.ScOrder;
import com.tonlp.sc.service.OrderService;
import com.tonlp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/order")
@RestController
public class OrderAction {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CommonResult<ScOrder> create(@RequestBody ScOrder scOrder){
        scOrder.setId(IdUtil.getSnowflake(1, 1).nextId());
        orderService.create(scOrder);
        return new CommonResult(200, "创建成功", scOrder);
    }

}
