package com.gradprj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String index(){
        System.out.println("index");
        return "index";
    }
}
