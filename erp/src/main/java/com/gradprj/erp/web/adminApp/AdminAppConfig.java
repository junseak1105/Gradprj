package com.gradprj.erp.web.adminApp;

import com.gradprj.erp.web.adminApp.DAO.*;
import com.gradprj.erp.web.adminApp.DTO.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminAppConfig {

    @Bean
    public PageRepository PageRepository(){
        return new MemoryPageRepository();
    }

    @Bean
    public SortcodeRepository SortcodeRepository(){return new MemorySortcodeRepository(); }

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
    Page_Get_Category_Service page_get_category_service() {
        return new Page_Get_Category_Service(PageRepository());
    }

    @Bean
    Page_Get_Sort_lv1_Service page_get_sort_lv1_service() {
        return new Page_Get_Sort_lv1_Service(SortcodeRepository());
    }

    @Bean
    Page_Get_Sort_lv2_Service page_get_sort_lv2_service() {
        return new Page_Get_Sort_lv2_Service(SortcodeRepository());
    }

    @Bean
    Page_Get_Sort_code_Service page_get_sort_code_service() {
        return new Page_Get_Sort_code_Service(SortcodeRepository());
    }

    @Bean
    Page_Delete_Sort_Service page_delete_sort_service() {
        return new Page_Delete_Sort_Service(SortcodeRepository());
    }

}
