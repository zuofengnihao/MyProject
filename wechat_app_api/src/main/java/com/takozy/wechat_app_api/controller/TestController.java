package com.takozy.wechat_app_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String method(String requestText, String currentTime) {
        System.out.println(currentTime);
        System.out.println(requestText);
        return "hello wechat app !";
    }

    @RequestMapping("/")
    public String method_1() {
        return "{\"msg\":\"hello!\"}";
    }
}
