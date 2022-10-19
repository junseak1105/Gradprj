package com.gradprj.erp.service;


import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import com.gradprj.erp.domain.TableInfo;
import com.gradprj.erp.mapper.TableInfo_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableInfo_Service {

    @Autowired
    private TableInfo_Mapper tableInfo_mapper;

    @Autowired
    private CodeList_Service codeList_service;

    public DefaultRes getTableInfo(String page_name) {
        Map<String, Object> resultMap = new HashMap<>();
        String table_name = tableInfo_mapper.getTableName(page_name);
        List<TableInfo> tableInfoList = tableInfo_mapper.gettableinfo(table_name);
        resultMap.put("data", tableInfoList);
        /*
            외래키 정보는 테이블의 컬럼명과 외래키가 참조하는 테이블의 컬럼명을 가져옴
         */
        for (TableInfo tableInfo : tableInfoList) {
            if (tableInfo.getRef_Table() != null) {
                Map<String,Object> refTableInfo = new HashMap<>();
                refTableInfo.put("info", tableInfo_mapper.gettableinfo(tableInfo.getRef_Table()));
                resultMap.put(tableInfo.getRef_Table(), refTableInfo);
            }
        }
        resultMap.put("table_name", table_name);
        resultMap.put("key_column", tableInfo_mapper.getKeyColumn(table_name));
        resultMap.put("code", codeList_service.findByCodeTable(table_name));
        if(resultMap.isEmpty()){
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, resultMap);
    }
}
