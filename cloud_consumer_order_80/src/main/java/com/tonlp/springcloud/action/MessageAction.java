package com.tonlp.springcloud.action;

import com.tonlp.springcloud.entities.CommonResult;
import com.tonlp.springcloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/msg")
@RestController
public class MessageAction {

    @Autowired
    private MessageService messageService;

    @GetMapping("/sendMsg")
    public CommonResult sendMsg(@RequestParam(required = false) String msg){
        if(msg == null){
            msg = "default";
        }
        boolean f = messageService.sendMsg(msg);
        return new CommonResult(200, "发送 "+f);
    }

}
