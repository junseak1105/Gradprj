package com.jhk.Spring.web.table;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/table")
public class TableController {

    @RequestMapping("/AddPage")
    public String AddPage(){
        return "/addpage";
    }

}
