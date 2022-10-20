package com.gradprj.erp.mapper;

import com.gradprj.erp.domain.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TableInfo_Mapper {

    @Select("call get_table_info(#{table_name})")
    List<TableInfo> gettableinfo(String table_name);

    @Select("call getKeyColumn(#{table_name})")
    String getKeyColumn(String tablename);

    @Select("call getTableName(#{page_name})")
    String getTableName(String page_name);

    @Select("select * from ${tablename}")
    List<Map<String,String>> getFKData(String tablename);

}
