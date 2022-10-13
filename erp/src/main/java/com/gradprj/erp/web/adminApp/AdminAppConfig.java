package com.gradprj.erp.web.adminApp;

import com.gradprj.erp.web.adminApp.Service.*;
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



    @Bean//페이지 생성 서비스
    Admin_Create_Service page_create_service() {
        return new Admin_Create_Service(PageRepository());
    }

    @Bean
    Admin_Name_Dupchk_Service page_name_dupchk_service() {
        return new Admin_Name_Dupchk_Service(PageRepository());
    }

    @Bean
    Admin_Get_List_Service page_get_list_service() {
        return new Admin_Get_List_Service(PageRepository());
    }

    @Bean
    Admin_Get_Category_Service page_get_category_service() {
        return new Admin_Get_Category_Service(PageRepository());
    }

    @Bean
    Admin_Get_Sort_lv1_Service page_get_sort_lv1_service() {
        return new Admin_Get_Sort_lv1_Service(SortcodeRepository());
    }

    @Bean
    Admin_Get_Sort_lv2_Service page_get_sort_lv2_service() {
        return new Admin_Get_Sort_lv2_Service(SortcodeRepository());
    }

    @Bean
    Admin_Get_Sort_code_Service page_get_sort_code_service() {
        return new Admin_Get_Sort_code_Service(SortcodeRepository());
    }

    @Bean
    Admin_Delete_Sort_Service page_delete_sort_service() {
        return new Admin_Delete_Sort_Service(SortcodeRepository());
    }

    @Bean
    Admin_Delete_Page_Service page_delete_page_service() {
        return new Admin_Delete_Page_Service();
    }
}
