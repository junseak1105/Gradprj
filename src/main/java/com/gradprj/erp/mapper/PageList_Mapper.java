package com.gradprj.erp.mapper;

import com.gradprj.erp.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PageList_Mapper {

    @Select("SELECT page_name, use_table, category_name FROM sm_cri_page_list")
    List<PageList> findAll();

    @Select("SELECT page_name, use_table, category_name FROM sm_cri_page_list WHERE page_name = #{page_name}")
    PageList findByPageName(String page_name);

}
