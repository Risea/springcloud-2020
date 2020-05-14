package com.tonlp.springcloud.service;


import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;


// 定义一个消息生产者 Source为生产者
@EnableBinding(Source.class)
@Slf4j
public class MessageService {

    // 消息发送管道
    @Autowired
    private MessageChannel output;


    public boolean sendMsg(String msg){
        output.send(genMessage(msg));
        return true;
    }

    Message genMessage(String msg){
        String uuid = IdUtil.simpleUUID();
        log.info("---------> "+uuid+": "+msg);
        return MessageBuilder.withPayload(uuid+":"+msg).build();
    }
}
