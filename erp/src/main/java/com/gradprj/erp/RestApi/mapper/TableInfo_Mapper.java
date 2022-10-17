package com.gradprj.erp.RestApi.mapper;

import com.gradprj.erp.RestApi.domain.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TableInfo_Mapper {

    @Select("call get_fks(#{table_name})")
    List<TableInfo> getFks(String table_name);

}
