package com.gradprj.erp;

import com.gradprj.erp.web.dbApp.DB_Service;
import com.gradprj.erp.web.adminApp.AdminApp;
import com.gradprj.erp.web.tableApp.TableApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * App 빈에 등록 모음
 */
@Configuration
public class BaseAppConfig {
    @Bean
    TableApp tableApp() throws Exception {
        return new TableApp();
    }

    @Bean
    AdminApp adminApp() throws Exception {
        return new AdminApp();
    }

    @Bean
    public DB_Service db_execute_service(){
        return new DB_Service();
    }

}
