package com.gradprj.erp.deprecated.RestApi.Deprecated.service;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Company_Reg;
import com.gradprj.erp.deprecated.RestApi.Deprecated.mapper.SM_Company_Reg_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_Company_Reg_Service {
    @Autowired
    private SM_Company_Reg_Mapper sm_company_reg_mapper;

    public DefaultRes findByComRegNo(String Company_Code) {
        final SM_Company_Reg comRegList = sm_company_reg_mapper.findByCom_Reg_Num(Company_Code);
        if(comRegList==null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, comRegList);
    }

    public DefaultRes findAll() {
        final List<SM_Company_Reg> comRegList = sm_company_reg_mapper.findAll();
        if(comRegList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, comRegList);
    }

    public DefaultRes save(SM_Company_Reg sm_company_reg) {
        sm_company_reg_mapper.save(sm_company_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_Company_Reg sm_company_reg) {
        sm_company_reg_mapper.update(sm_company_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String Company_Code) {
        sm_company_reg_mapper.delete(Company_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }
}
