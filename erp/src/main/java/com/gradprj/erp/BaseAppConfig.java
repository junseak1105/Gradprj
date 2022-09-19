package com.gradprj.erp;

import com.gradprj.erp.web.dbApp.DB_Service;
import com.gradprj.erp.web.tableApp.TableApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseAppConfig {
    @Bean
    TableApp tableApp() throws Exception {
        return new TableApp();
    }

    @Bean
    public DB_Service db_execute_service(){
        return new DB_Service();
    }

}
