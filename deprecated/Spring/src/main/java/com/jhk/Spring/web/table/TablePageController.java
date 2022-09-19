package com.jhk.Spring.web.table;

import com.jhk.Spring.TableApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@RequestMapping("/table")
public class TablePageController {

    @GetMapping("/create")
    public String table_create(){
        System.out.println("table_create");
        return "index";
    }
}
