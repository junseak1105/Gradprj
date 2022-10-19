package com.gradprj.erp.service;

import com.gradprj.erp.mapper.PageCategory_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageCategory_Service {

    @Autowired
    private PageCategory_Mapper pageCategory_mapper;

    public DefaultRes findAll() {
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, pageCategory_mapper.findAll());
    }



}
