package edu.miu.cs.cs544.exercise15x.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @GetMapping
    public String getAllActors() {
        return "walah ";
    }


}
