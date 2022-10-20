package com.gradprj.erp.service;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.domain.PageList;

public interface PageList_Service {

    DefaultRes findAll();
    PageList findByPageName(String page_name);

}
