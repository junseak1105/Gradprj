package com.jhk.Spring.web.table;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/table")
public class TablePageController {

    @RequestMapping("/AddPage")
    public String AddPage(){
        return "/addpage";
    }



}
