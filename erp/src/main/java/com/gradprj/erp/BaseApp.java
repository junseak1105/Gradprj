package com.gradprj.erp;


import com.gradprj.erp.web.dbApp.DB_Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BaseApp {

    private static ApplicationContext baseapp = new AnnotationConfigApplicationContext(BaseAppConfig.class);
    //DB_Execute_Service 생성
    public static DB_Service db_service = baseapp.getBean("db_execute_service", DB_Service.class);

}
