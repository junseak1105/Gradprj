package com.jhk.Spring;

import com.jhk.Spring.web.DBService.DB_Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBAppConfig {
    @Bean
    public DB_Service db_execute_service(){
        return new DB_Service();
    }
}
