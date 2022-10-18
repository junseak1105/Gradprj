package com.gradprj.erp.RestApi.mapper;


import com.gradprj.erp.RestApi.domain.TableCriteria;
import com.gradprj.erp.RestApi.domain.TableData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TableData_Mapper {

    @Select("SELECT * FROM ${tablename}")
    List<Map<String,String>> findAll(String tablename);

    @Select("SELECT * FROM ${tablename} WHERE ${key} = ${value} ${order}")
    List<Map<String,String>> findByKey(String tablename, String key, String value, String order);

    @Select("DELETE FROM ${tablename} WHERE ${key} = ${value}")
    void delete(String tablename, String key, String value);

    @Select("INSERT INTO ${tablename} " +
            "(${column}) " +
            "VALUES " +
            "(${value})"
    )
    void save(String tablename, String column, String value);

    @Select("SELECT * from ${tablename} WHERE ${key} = ${value}")
    List<Map<String,String>> getListWithPaging(TableCriteria cri);


    @Update("UPDATE ${tablename} SET ${set} WHERE ${key_column} = ${key_value}")
    void update(String tablename, String set,String key_column, String key_value);

}

