package com.tonlp.springcloud.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableBinding(Sink.class)
@RequestMapping("/msg")
@Slf4j
@RestController
public class MessageAction {

    @Value("${server.port}")
    private String serverPort;

    //
    @StreamListener(Sink.INPUT)
    public void getMsg(Message<String> message){
        log.info("server {} getMsg --> {}", serverPort, message.getPayload());
    }

}
