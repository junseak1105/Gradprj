package com.gradprj.erp.RestApi.service;

import com.gradprj.erp.RestApi.domain.SM_Department_Reg;
import com.gradprj.erp.RestApi.mapper.SM_Department_Reg_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_Department_Reg_Service {

    @Autowired
    private SM_Department_Reg_Mapper sm_department_reg_mapper;

    public DefaultRes findAll() {
        final List<SM_Department_Reg> deptRegList = sm_department_reg_mapper.findAll();
        if(deptRegList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, deptRegList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, deptRegList);
    }

    public DefaultRes findByDepartment_Code(String Department_Code) {
        final SM_Department_Reg deptReg = sm_department_reg_mapper.findByDepartment_Code(Department_Code);
        if(deptReg==null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, deptReg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, deptReg);
    }

    public DefaultRes save(SM_Department_Reg sm_department_reg) {
        sm_department_reg_mapper.save(sm_department_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_Department_Reg sm_department_reg) {
        sm_department_reg_mapper.update(sm_department_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String Department_Code) {
        sm_department_reg_mapper.delete(Department_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }
}
