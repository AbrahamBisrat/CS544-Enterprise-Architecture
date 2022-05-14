package edu.miu.cs544.cloud.demo.server.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // The whole idea here is real-time refreshing of configs
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Value("${test.name}")
    private String envString;

    @GetMapping
    public String test() {
        return "keyword is : " + envString;
    }

}
