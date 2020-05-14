package com.tonlp.sc.action;

import com.tonlp.sc.service.StorageService;
import com.tonlp.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/storage")
@RestController
public class StorageAction {

    @Autowired
    private StorageService storageService;

    @PostMapping("/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200, "库存扣除成功");
    }

}
