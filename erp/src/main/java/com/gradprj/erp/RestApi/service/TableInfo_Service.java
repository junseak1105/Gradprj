package com.gradprj.erp.RestApi.service;


import com.gradprj.erp.RestApi.domain.TableInfo;
import com.gradprj.erp.RestApi.mapper.TableInfo_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableInfo_Service {

    @Autowired
    private TableInfo_Mapper tableInfo_mapper;

    public Map getTableInfo(String table_name) {
        Map<String, Object> resultMap = new HashMap<>();
        List<TableInfo> tableInfoList = tableInfo_mapper.get_table_info(table_name);
        resultMap.put("data", tableInfoList);
        for (TableInfo tableInfo : tableInfoList) {
            if (tableInfo.getRef_Table() != null) {
                Map<String,Object> refTableInfo = new HashMap<>();
                refTableInfo.put("info", tableInfo_mapper.get_table_info(tableInfo.getRef_Table()));
                refTableInfo.put("data", tableInfo_mapper.getTableInfo(tableInfo.getRef_Table()));
                resultMap.put(tableInfo.getRef_Table(), refTableInfo);
            }
        }

        return resultMap;
    }

    public String getKeyColumn(String tablename) {
        return tableInfo_mapper.get_table_info(tablename).get(0).getColumn_Name();
    }
}
