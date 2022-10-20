package com.gradprj.erp.mapper;

import com.gradprj.erp.domain.CodeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CodeList_Mapper {

    @Select("SELECT * FROM sm_cri_codedlist_reg where code_table = #{code_table}")
    CodeList findByCodeTable(String code_table);
}
