package com.gradprj.erp.service;

import com.gradprj.erp.domain.CodeList;

public interface CodeList_Service {
    CodeList findByCodeTable(String code_table);
}
