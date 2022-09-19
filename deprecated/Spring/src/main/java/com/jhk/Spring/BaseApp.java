package com.jhk.Spring;

import com.jhk.Spring.web.DBService.DB_Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BaseApp {

    //DB_Execute_Service 생성
    private static ApplicationContext DBapplicationContext = new AnnotationConfigApplicationContext(DBAppConfig.class);

//    db_service 생성
     protected static DB_Service db_service = DBapplicationContext.getBean("db_execute_service", DB_Service.class);
}
