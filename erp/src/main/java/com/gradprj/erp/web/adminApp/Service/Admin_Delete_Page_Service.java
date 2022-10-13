package com.gradprj.erp.web.adminApp.Service;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DTO.Page;
import com.gradprj.erp.web.adminApp.DTO.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Admin_Delete_Page_Service extends BaseApp implements Admin_Service {

    @Override
    public String Execute(String condition) {
        String query = "DELETE FROM page_list " + condition;
        System.out.println(query);
        if(db_service.DB_Ex_query_nr(query)){
            return "success";
        }else{
            return "fail";
        }
    }
}