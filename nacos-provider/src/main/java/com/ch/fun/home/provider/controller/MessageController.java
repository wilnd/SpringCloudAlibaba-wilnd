package com.ch.fun.home.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.fun.home.provider.service.SenderService;
import com.ch.fun.home.provider.service.SenderTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private SenderService senderService;

    @Autowired
    private SenderTagService senderTagService;

    @RequestMapping("/senderService")
    public String sendNormalMsg(@RequestParam("message") String message) {
        senderService.sendMessage(message);
        return "发送普通消息成功, 流水号：" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    @RequestMapping(value = "/sendObject")
    public String sendObject() throws Exception {
        JSONObject ss = new JSONObject();
        ss.put("name", "name");
        senderTagService.sendObject(ss, "myTag");
        return "ok";
    }

}
