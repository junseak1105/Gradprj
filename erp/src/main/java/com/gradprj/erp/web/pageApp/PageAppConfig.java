package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.web.pageApp.Service.Datagrid_Get_Columns;
import com.gradprj.erp.web.pageApp.Service.Datagrid_Get_Data;
import com.gradprj.erp.web.pageApp.Service.Datagrid_Save_Data;
import com.gradprj.erp.web.pageApp.Service.Page_Get_Tablename;
import com.gradprj.erp.web.pageApp.DTO.MemoryPageDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageAppConfig {

    @Bean
    MemoryPageDataRepository memoryPageDataRepository(){
        return new MemoryPageDataRepository();
    }

    @Bean
    Page_Get_Tablename page_get_tablename(){
        return new Page_Get_Tablename();
    }
    @Bean
    Datagrid_Get_Columns datagrid_get_columns(){
        return new Datagrid_Get_Columns(memoryPageDataRepository());
    }

    @Bean
    Datagrid_Get_Data datagrid_get_data(){
        return new Datagrid_Get_Data(memoryPageDataRepository());
    }

    @Bean
    Datagrid_Save_Data datagrid_save_data(){
        return new Datagrid_Save_Data();
    }
}
