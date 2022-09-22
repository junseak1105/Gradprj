package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.web.pageApp.DAO.*;
import com.gradprj.erp.web.pageApp.DTO.DatagridRepository;
import com.gradprj.erp.web.pageApp.DTO.MemoryDatagridRepository;
import com.gradprj.erp.web.pageApp.DTO.MemoryPageRepository;
import com.gradprj.erp.web.pageApp.DTO.PageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageAppConfig {

    @Bean
    public PageRepository PageRepository(){
        return new MemoryPageRepository();
    }

    @Bean
    public DatagridRepository DatagridRepository(){
        return new MemoryDatagridRepository();
    }

    @Bean//데이터그리드 fetch 서비스
    Page_Get_Datagrid_Service page_get_datagrid_service() {
        return new Page_Get_Datagrid_Service(DatagridRepository());
    }

    @Bean//페이지 생성 서비스
    Page_Create_Service page_create_service() {
        return new Page_Create_Service(PageRepository());
    }

    @Bean
    Page_Name_Dupchk_Service page_name_dupchk_service() {
        return new Page_Name_Dupchk_Service(PageRepository());
    }

    @Bean
    Page_Get_List_Service page_get_list_service() {
        return new Page_Get_List_Service(PageRepository());
    }

    @Bean
    Page_Get_Category_Service page_get_category_service() { return new Page_Get_Category_Service(PageRepository()); }

}
