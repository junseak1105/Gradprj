package com.gradprj.erp.mapper;

import com.gradprj.erp.domain.AddiPage;
import com.gradprj.erp.domain.PrintList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrintList_Mapper {

    @Select("select * from sm_cri_print_list")
    List<PrintList> findAll();

    @Select("select print_name,print_head,print_foot from sm_cri_print_list left join sm_cri_page_list scapl on sm_cri_print_list.print_name = scapl.page_print\n" +
            "where page_code =  #{pagecode}")
    PrintList getPrintList(String pagecode);

}
