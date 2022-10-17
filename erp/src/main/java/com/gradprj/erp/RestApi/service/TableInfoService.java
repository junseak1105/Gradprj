package com.gradprj.erp.RestApi.service;


import com.gradprj.erp.RestApi.domain.TableInfo;
import com.gradprj.erp.RestApi.mapper.TableInfo_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableInfoService {

    @Autowired
    private TableInfo_Mapper tableInfo_mapper;

    public DefaultRes getTableInfo(String table_name){
        List<TableInfo> tableInfoList = tableInfo_mapper.getFks(table_name);
        if(tableInfoList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, tableInfoList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, tableInfoList);
    }
}
