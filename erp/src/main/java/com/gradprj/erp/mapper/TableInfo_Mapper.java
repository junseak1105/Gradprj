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
    List<TableInfo> get_table_info(String table_name);

    @Select("select * from ${table_name}")
    List<Map<String,String>> getTableInfo(String table_name);

}
