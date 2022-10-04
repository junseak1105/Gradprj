package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.BaseApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/page")
public class PageAppController extends BaseApp {

    @RequestMapping("/showPage/{path}")
    @ResponseBody
    public String userApp(@PathVariable String path){
        return pageApp.GetPage(path);
    }

    @RequestMapping("/showNavTree")
    @ResponseBody
    public String navbar(){
        return pageApp.NavTree();
    }
}
