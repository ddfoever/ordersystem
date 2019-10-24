package com.ddfoever.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort(){
        return "当前的端口号："+this.port;
    }
}
