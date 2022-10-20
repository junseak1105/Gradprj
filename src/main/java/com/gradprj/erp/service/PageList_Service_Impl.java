package com.gradprj.erp.service;


import com.gradprj.erp.domain.PageList;
import com.gradprj.erp.mapper.PageList_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PageList_Service_Impl implements PageList_Service {

    @Autowired
    private PageList_Mapper pageList_mapper;

    @Override
    @Transactional
    public DefaultRes findAll() {
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, pageList_mapper.findAll());
    }

    @Override
    @Transactional
    public PageList findByPageName(String page_name) {
        return pageList_mapper.findByPageName(page_name);
    }
}
