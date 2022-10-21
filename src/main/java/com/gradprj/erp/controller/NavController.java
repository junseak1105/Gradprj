package com.gradprj.erp.controller;


import com.gradprj.erp.domain.TableCriteria;
import com.gradprj.erp.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import sun.tools.jconsole.JConsole;

@Controller
@ApiIgnore
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

    @GetMapping("/list/{page_code}")
    public ModelAndView list(@PathVariable String page_code) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        return mv;
    }

}
