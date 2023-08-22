package com.example.test01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSpringBoot {
    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "Hello Spring Boot spring branch!!";
    }
}
