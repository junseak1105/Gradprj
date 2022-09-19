package com.gradprj.web.web.table;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class TablePageController {

    @GetMapping("/create")
    public String table_create(){
        System.out.println("table_create");
        return "index";
    }
}
