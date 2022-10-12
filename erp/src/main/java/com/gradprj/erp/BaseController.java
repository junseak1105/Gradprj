package com.gradprj.erp;

import com.gradprj.erp.web.dbApp.DB_Service;
import com.gradprj.erp.web.adminApp.AdminApp;
import com.gradprj.erp.web.tableApp.TableApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 모든 Controller의 부모 클래스
 */
@Controller
@RequestMapping("/")
public class BaseController {

    @RequestMapping("")
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("main")
    public String main(){
        System.out.println("main");
        return "main";
    }



}
