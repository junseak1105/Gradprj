package com.gradprj.erp.service;

import com.gradprj.erp.domain.AddiPage;

import java.util.List;


public interface AddiPage_Service {
    List<AddiPage> findAll();
    String getAddipagelink(String pagecode);
}
