package com.gradprj.erp.web.adminApp.DAO;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DTO.Page;
import com.gradprj.erp.web.adminApp.DTO.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Page_Create_Service extends BaseApp implements Page_Service {
    private final PageRepository pageRepository;

    @Autowired
    public Page_Create_Service(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public String Execute(String condition) {
        Page page = (Page) pageRepository.findAll().get(0);
        String query = "INSERT INTO page_list(page_name,page_desc,page_url,page_table,page_category)" +
                "VALUES('" + page.getPage_name() + "','" + page.getPage_desc() + "','" + page.getPage_url() + "','" + page.getPage_table() + "','" + page.getPage_category() + "')";
        System.out.println(query);
        if(db_service.DB_Ex_query_nr(query)){
            return "success";
        }else{
            return "fail";
        }
    }
}