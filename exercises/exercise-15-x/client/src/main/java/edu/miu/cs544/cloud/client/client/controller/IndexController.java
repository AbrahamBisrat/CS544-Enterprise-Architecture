package edu.miu.cs544.cloud.client.client.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "Index controller - just to keep the service running";
    }

}
