package com.gradprj.erp.mapper;


import com.gradprj.erp.domain.PageCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface PageCategory_Mapper {

    @Select("SELECT category_name, category_explain FROM sm_cri_page_category order by category_sort ")
    List<PageCategory> findAll();

}
