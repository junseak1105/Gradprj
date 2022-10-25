package com.gradprj.erp.mapper;

import com.gradprj.erp.domain.AddiPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddiPage_Mapper {

    @Select("select * from SM_CRI_ADDI_PAGE_LIST")
    List<AddiPage> findAll();

    @Select("select Addi_Page_Link from sm_cri_page_list left join sm_cri_addi_page_list scapl on sm_cri_page_list.Addi_Page_Name = scapl.Addi_Page_Name\n" +
            "where page_code =  #{pagecode}")
    String getAddiPageLink(String pagecode);

}
