package com.gradprj.erp.RestApi.Deprecated.service;

import com.gradprj.erp.RestApi.Deprecated.domain.SM_House_Reg;
import com.gradprj.erp.RestApi.Deprecated.mapper.SM_House_Reg_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_House_Reg_Service {

    @Autowired
    private SM_House_Reg_Mapper sm_house_reg_mapper;

    public DefaultRes save(SM_House_Reg sm_house_reg) {
        sm_house_reg_mapper.save(sm_house_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_House_Reg sm_house_reg) {
        sm_house_reg_mapper.update(sm_house_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String House_Code) {
        sm_house_reg_mapper.delete(House_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }

    public DefaultRes findAll() {
        final List<SM_House_Reg> houseList = sm_house_reg_mapper.findAll();
        if (houseList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.DB_ERROR);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found ,houseList);
    }

    public DefaultRes getHouseByHouseCode(String House_Code) {
        final SM_House_Reg house = sm_house_reg_mapper.findByHouse_Code(House_Code);
        if (house == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, house);
    }
}
