package com.gradprj.erp.service;


import com.gradprj.erp.domain.AddiPage;
import com.gradprj.erp.domain.PrintList;
import com.gradprj.erp.mapper.PrintList_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintList_Service_Impl implements PrintList_Service {


    @Autowired
    private PrintList_Mapper printList_mapper;

    @Override
    public List<PrintList> findAll() {
        return printList_mapper.findAll();
    }

    @Override
    public PrintList getPrintList(String pagecode) {
        return printList_mapper.getPrintList(pagecode);
    }
}
