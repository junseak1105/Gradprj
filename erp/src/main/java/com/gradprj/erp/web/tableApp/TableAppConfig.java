package com.gradprj.erp.web.tableApp;

import com.gradprj.erp.web.tableApp.DTO.MemoryTableRepository;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import com.gradprj.erp.web.tableApp.DAO.Table_Service;
import com.gradprj.erp.web.tableApp.DAO.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableAppConfig {


    @Bean//TableService 생성 서비스
    public Table_Service table_service(){
        return new Table_ServiceImpi(new MemoryTableRepository());
    }

    @Bean//TableRepository 단일 생성 서비스
    public TableRepository tableRepository(){
        return new MemoryTableRepository();
    }

    @Bean//테이블 생성 서비스
    public Table_Create_Service table_create_service(){
        return new Table_Create_Service(tableRepository());
    }

    @Bean//테이블 삭제 서비스
    public Table_Drop_Service table_drop_service(){
        return new Table_Drop_Service(tableRepository());
    }

    @Bean//테이블 명 중복체크 서비스
    Table_Name_Dupchk_Service table_name_dupchk_service(){
        return new Table_Name_Dupchk_Service(tableRepository());
    }

    @Bean//테이블 정보 가져오기 서비스
    Table_Get_info_Service table_get_info_service(){ return new Table_Get_info_Service(tableRepository());}

    @Bean//테이블 목록 가져오기 서비스
    Table_Get_list_Service table_get_list_service(){ return new Table_Get_list_Service(tableRepository());}

}
