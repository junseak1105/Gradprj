package com.gradprj.erp.mapper;


import com.gradprj.erp.domain.TableCriteria;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TableData_Mapper {

    @Select("SELECT * FROM ${tablename}")
    List<Map<String,String>> findAll(String tablename);

    @Select("SELECT * FROM ${tablename} WHERE ${key} = ${value} ${order}")
    List<Map<String,String>> findByKey(String tablename, String key, String value, String order);

    @Delete("DELETE FROM ${tablename} WHERE ${where}")
    boolean delete(String tablename,String where);

    @Insert("INSERT INTO ${tablename} " +
            "(${column}) " +
            "VALUES " +
            "(${value})"
    )
    boolean save(String tablename, String column, String value);

    @Select("SELECT * from ${tablename} WHERE ${key} = ${value}")
    List<Map<String,String>> getListWithPaging(TableCriteria cri);

//    @Update("UPDATE ${tablename} SET ${column}=${value} WHERE ${key_column} = ${key_value}")
//    boolean update(String tablename, String column, String value, String key_column, String key_value);

    @Update("UPDATE ${tablename} SET ${set} WHERE ${key_column} = ${key_value}")
    boolean update(String tablename, String set,String key_column, String key_value);

}

