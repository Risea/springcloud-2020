package com.tonlp.springclouda.exhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tonlp.springcloud.entities.CommonResult;

public class MyBlockHandler {

    public static CommonResult byCustomer1Handle(BlockException ex){
        return new CommonResult(200, "by Customer handle 1", ex.getMessage());
    }

    public static CommonResult byCustomer2Handle(BlockException ex){
        return new CommonResult(200, "by Customer handle 2", ex.getMessage());
    }
}
