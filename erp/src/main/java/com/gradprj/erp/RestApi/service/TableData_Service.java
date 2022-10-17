package com.gradprj.erp.RestApi.service;

import com.gradprj.erp.RestApi.mapper.TableData_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableData_Service {

    @Autowired
    private TableData_Mapper tableData_mapper;

    public DefaultRes deleteData(String table_name, String pk_name, String pk_value){
        tableData_mapper.delete(table_name, pk_name, pk_value);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found);
    }

    public DefaultRes saveData(String table_name, String column,String value){
        tableData_mapper.save(table_name, column, value);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found);
    }

    public DefaultRes getData(String table_name, String key, String value, String order){
        List<Map<String,String>> tableDataList = tableData_mapper.findByKey(table_name, key, value, order);
        if(tableDataList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, tableDataList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, tableDataList);
    }


    public DefaultRes getAllData(String table_name){
        List<Map<String,String>> tableDataList = tableData_mapper.findAll(table_name);
        if(tableDataList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessages.Data_Empty, tableDataList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, tableDataList);
    }
}
