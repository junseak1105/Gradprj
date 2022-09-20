package com.gradprj.erp.web.pageApp.DAO;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.pageApp.DTO.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Page_Create_Service extends BaseApp implements Page_Service {
    private final PageRepository pageRepository;

    @Autowired
    public Page_Create_Service(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public String Execute() {
        System.out.println("Page_Create_Service");
        return "Page_Create_Service";
    }
}