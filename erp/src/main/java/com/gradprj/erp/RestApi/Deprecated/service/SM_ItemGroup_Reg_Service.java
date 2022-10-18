package com.gradprj.erp.RestApi.Deprecated.service;

import com.gradprj.erp.RestApi.Deprecated.domain.SM_ItemGroup_Reg;
import com.gradprj.erp.RestApi.Deprecated.mapper.SM_ItemGroup_Reg_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_ItemGroup_Reg_Service {

    @Autowired
    private SM_ItemGroup_Reg_Mapper sm_itemgroup_reg_mapper;

    public DefaultRes findAll() {
        List<SM_ItemGroup_Reg> itemGroupList = sm_itemgroup_reg_mapper.findAll();
        if (itemGroupList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.DB_ERROR);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, itemGroupList);
    }

    public DefaultRes getItemGroupByItemGroupCode(String ItemGroup_Code) {
        SM_ItemGroup_Reg itemGroup = sm_itemgroup_reg_mapper.findByItemGroup_Code(ItemGroup_Code);
        if (itemGroup == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, itemGroup);
    }

    public DefaultRes save(SM_ItemGroup_Reg sm_itemgroup_reg) {
        sm_itemgroup_reg_mapper.save(sm_itemgroup_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_ItemGroup_Reg sm_itemgroup_reg) {
        sm_itemgroup_reg_mapper.update(sm_itemgroup_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String ItemGroup_Code) {
        sm_itemgroup_reg_mapper.delete(ItemGroup_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }
}
