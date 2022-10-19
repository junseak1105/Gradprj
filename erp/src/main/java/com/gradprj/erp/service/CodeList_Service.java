package com.gradprj.erp.service;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.domain.CodeList;
import com.gradprj.erp.mapper.CodeList_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeList_Service {

    @Autowired
    private CodeList_Mapper codeList_mapper;

    public CodeList findByCodeTable(String code_table) {
        if(codeList_mapper.findByCodeTable(code_table) == null) {
            return null;
        }
        return codeList_mapper.findByCodeTable(code_table);
    }
}
