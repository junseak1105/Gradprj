package com.gradprj.erp.web.pageApp;


import com.gradprj.erp.BaseApp;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PageApp extends BaseApp {
    //빈 가져오기용
    ApplicationContext UserAppConfig = new AnnotationConfigApplicationContext(PageAppConfig.class);

    public String NavTree() {
        JSONObject navbar = new JSONObject();

        return navbar.toJSONString();
    }
    public String GetPage(String path){
        JSONObject page = new JSONObject();


        return page.toJSONString();
    }
}
