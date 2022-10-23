package com.gradprj.erp.service;


import com.gradprj.erp.domain.AddiPage;
import com.gradprj.erp.mapper.AddiPage_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddiPage_Service_Impl implements AddiPage_Service {

    @Autowired
    private AddiPage_Mapper addiPage_mapper;

    @Override
    public List<AddiPage> findAll() {
        return addiPage_mapper.findAll();
    }

    @Override
    public String getAddipagelink(String pagecode) {
        return addiPage_mapper.getAddiPageLink(pagecode);
    }
}
