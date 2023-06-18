package com.minzu.minzustore.controller;

import com.minzu.minzustore.entity.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class TestController {


    @GetMapping("/test")
    public Msg test() {
        return Msg.ok("跑得通");
    }
}
