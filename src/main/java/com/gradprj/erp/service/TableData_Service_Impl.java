package com.gradprj.erp.service;

import com.gradprj.erp.mapper.TableData_Mapper;
import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Service
public class TableData_Service_Impl implements TableData_Service {

    @Autowired
    private TableData_Mapper tableData_mapper;


    @Override
    @Transactional
    public DefaultRes deleteData(String table_name, String key_column, String selected){
        String where = "";
        StringTokenizer st = new StringTokenizer(selected, ",");
        //다중 한번에 처리문 필요
        while (st.hasMoreTokens()) {
            where += key_column + " = '" + st.nextToken() + "' OR ";
        }
        where = where.substring(0, where.length() - 4);
        if(tableData_mapper.delete(table_name, where)){
            return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_deleted);
        }
        return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
    }
    @Override
    @Transactional
    public DefaultRes saveData(String table_name, String column,String value){

        if(tableData_mapper.save(table_name, column, value)){
            return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_saved);
        }
        return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
    }
    @Override
    @Transactional
    public DefaultRes updateData(String tablename, String column,String value, String key_column,String key_value){
        StringTokenizer st = new StringTokenizer(column, ",");
        StringTokenizer st2 = new StringTokenizer(value, ",");
//        while (st.hasMoreTokens()) {
//            if(!tableData_mapper.update(table_name, st.nextToken(), st2.nextToken(), key_column, "'"+key_value+"'")){
//                return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
//            }
//        }
//        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);

        String set = "";
        while(st.hasMoreTokens()){
            set += st.nextToken() + " = " + st2.nextToken() + ", ";
        }
        set = set.substring(0, set.length()-2);
        if(tableData_mapper.update(tablename, set, key_column, "'"+key_value+"'")){
            return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_updated);
        }
        return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
    }
    @Override
    @Transactional
    public DefaultRes getData(String table_name, String key, String value, String order){
        List<Map<String,String>> tableDataList = tableData_mapper.findByKey(table_name, key, value, order);
        if(tableDataList.isEmpty())
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.Data_Empty, tableDataList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.DB_ERROR, tableDataList);
    }

    @Override
    @Transactional
    public DefaultRes getAllData(String table_name){
        List<Map<String,String>> tableDataList = tableData_mapper.findAll(table_name);
        if(tableDataList.isEmpty())
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.Data_Empty, tableDataList);
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, tableDataList);
    }
}
