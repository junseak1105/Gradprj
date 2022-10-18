package com.gradprj.erp.RestApi.Deprecated.service;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.RestApi.Deprecated.domain.SM_Zipcode;
import com.gradprj.erp.config.StatusCode;
import com.gradprj.erp.RestApi.Deprecated.mapper.SM_Zipcode_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SM_Zipcode_Service {

    @Autowired
    private SM_Zipcode_Mapper sm_zipcode_mapper;

    public DefaultRes findAll() {
        final List<SM_Zipcode> zipList = sm_zipcode_mapper.findAll();
        if (zipList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.DB_ERROR);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found ,zipList);
    }

    public DefaultRes getZipByZipCode(String ZipCode) {
        final SM_Zipcode zip = sm_zipcode_mapper.findByZipCode(ZipCode);
        if (zip == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, zip);
    }

    public DefaultRes save(SM_Zipcode sm_zipcode) {
        sm_zipcode_mapper.save(sm_zipcode);
        return DefaultRes.res(StatusCode.CREATED, ResponseMessages.Data_saved);
    }

    public DefaultRes update(SM_Zipcode sm_zipcode) {
        sm_zipcode_mapper.update(sm_zipcode);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
    }

    public DefaultRes delete(String ZipCode) {
        sm_zipcode_mapper.delete(ZipCode);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
    }


}
