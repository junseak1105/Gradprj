package com.gradprj.erp.controller;


import com.gradprj.erp.domain.PageList;
import com.gradprj.erp.domain.TableCriteria;
import com.gradprj.erp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Controller
public class NavController {

    @Autowired
    private TableData_Service tableData_service;
    @Autowired
    private TableInfo_Service tableInfo_service;

    @Autowired
    private CodeList_Service codeList_service;

    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dashboard");
        return mv;
    }

    @GetMapping("/api/usage")
    public String api() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/list/{pagename}")
    public ModelAndView list(@PathVariable String pagename, TableCriteria cri) {
        ModelAndView mv = new ModelAndView();
//        String tablename = (pageList_service.findByPageName(pagename)).getUse_table();
//        mv.addObject("tablename", tablename);
//        mv.addObject("pagename", pagename);
//        mv.addObject("code", codeList_service.findByCodeTable(tablename));
//        mv.addObject("key_column", tableInfo_service.getKeyColumn(tablename));
//        mv.addObject("tabledata", tableData_service.getAllData(tablename));
//        mv.addObject("tableinfo", tableInfo_service.getTableInfo(tablename));
        mv.setViewName("list");
        return mv;
    }

    @GetMapping("/listtemp/{pagename}")
    public String listtemp(@PathVariable String pagename) {
        return "listtemp";
    }


}
