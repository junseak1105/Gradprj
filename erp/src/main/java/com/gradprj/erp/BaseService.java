package com.gradprj.erp;

import com.gradprj.erp.web.dbApp.DB_Service;
import com.gradprj.erp.web.tableApp.TableApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 모든 Controller의 부모 클래스
 */
public class BaseService {

    /**
     * BaseApp생성
     * - BaseApp을 통해 모든 App 불러옴
     * - BaseAppConfig에 모든 App Bean 등록
     */
    private static final ApplicationContext baseapp = new AnnotationConfigApplicationContext(BaseAppConfig.class);

    /**
     * App생성
     */
    protected static TableApp tableApp = baseapp.getBean("tableApp", TableApp.class);
    public static DB_Service db_service = baseapp.getBean("db_execute_service", DB_Service.class);


}
