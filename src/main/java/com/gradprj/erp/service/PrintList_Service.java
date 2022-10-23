package com.gradprj.erp.service;

import com.gradprj.erp.domain.AddiPage;
import com.gradprj.erp.domain.PrintList;

import java.util.List;


public interface PrintList_Service {
    List<PrintList> findAll();
    PrintList getPrintList(String pagecode);
}
