package com.gradprj.erp.deprecated.RestApi.Deprecated.service;

import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Employee_Reg;
import com.gradprj.erp.deprecated.RestApi.Deprecated.mapper.SM_Employee_Reg_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_Employee_Reg_Service {

    @Autowired
    private SM_Employee_Reg_Mapper sm_employee_reg_mapper;

    public DefaultRes findAll() {
        final List<SM_Employee_Reg> empRegList = sm_employee_reg_mapper.findAll();
        if (empRegList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, empRegList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, empRegList);
    }

    public DefaultRes findByEmployee_Code(String Employee_Code) {
        final SM_Employee_Reg empReg = sm_employee_reg_mapper.findByEmployee_Code(Employee_Code);
        if (empReg == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, empReg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, empReg);
    }

    public DefaultRes save(SM_Employee_Reg sm_employee_reg) {
        sm_employee_reg_mapper.save(sm_employee_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_Employee_Reg sm_employee_reg) {
        sm_employee_reg_mapper.update(sm_employee_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String Employee_Code) {
        sm_employee_reg_mapper.delete(Employee_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }
}
