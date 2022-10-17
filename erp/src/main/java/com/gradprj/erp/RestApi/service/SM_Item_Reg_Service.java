package com.gradprj.erp.RestApi.service;

import com.gradprj.erp.RestApi.domain.SM_Item_Reg;
import com.gradprj.erp.RestApi.mapper.SM_Item_Reg_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_Item_Reg_Service {

    @Autowired
    private SM_Item_Reg_Mapper sm_item_reg_mapper;

    public DefaultRes findAll() {
        List<SM_Item_Reg> itemList = sm_item_reg_mapper.findAll();
        if (itemList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.DB_ERROR);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, itemList);
    }

    public DefaultRes getItemByItemCode(String Item_Code) {
        SM_Item_Reg item = sm_item_reg_mapper.findByItemCode(Item_Code);
        if (item == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, item);
    }

    public DefaultRes save(SM_Item_Reg sm_item_reg) {
        sm_item_reg_mapper.save(sm_item_reg);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_Item_Reg sm_item_reg) {
        sm_item_reg_mapper.update(sm_item_reg);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String Item_Code) {
        sm_item_reg_mapper.delete(Item_Code);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }
}
