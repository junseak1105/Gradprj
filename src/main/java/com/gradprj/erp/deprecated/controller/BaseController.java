package com.gradprj.erp.deprecated.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 모든 Controller의 부모 클래스
 */

@ApiIgnore
@Controller
@Log4j2
public class BaseController {

    @RequestMapping("/admin/{urlid}")
    public String admin_main(@PathVariable("urlid") String urlid){
        return "/admin/"+urlid;
    }

    @RequestMapping("/datagrid/{path}")
    public ModelAndView pageApp(@PathVariable String path, HttpServletRequest request) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("pagename",path);
        mv.setViewName("page/datagrid");
        return mv;
    }
}
