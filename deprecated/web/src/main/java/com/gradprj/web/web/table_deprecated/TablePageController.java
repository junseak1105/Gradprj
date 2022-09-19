package com.gradprj.web.web.table_deprecated;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/table")
public class TablePageController {

    @RequestMapping("/AddPage")
    public String AddPage(){
        return "/addpage";
    }



}
