package com.jhk.Spring;

import com.jhk.Spring.web.table.MemoryTableRepository;
import com.jhk.Spring.web.table.TableRepository;
import com.jhk.Spring.web.table.Table_Service;
import com.jhk.Spring.web.table.service.Table_Create_Service;
import com.jhk.Spring.web.table.service.Table_Drop_Service;
import com.jhk.Spring.web.table.service.Table_ServiceImpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableAppConfig {

    //TableRepository 생성 서비스
    @Bean
    public Table_Service table_service(){
        return new Table_ServiceImpi(new MemoryTableRepository());
    }

    @Bean
    public TableRepository tableRepository(){
        return new MemoryTableRepository();
    }

    @Bean
    public Table_Create_Service table_create_service(){
        return new Table_Create_Service(tableRepository());
    }

    @Bean
    public Table_Drop_Service table_drop_service(){
        return new Table_Drop_Service(tableRepository());
    }
}
