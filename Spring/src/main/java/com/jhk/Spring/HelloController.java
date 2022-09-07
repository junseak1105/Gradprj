package com.jhk.Spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
