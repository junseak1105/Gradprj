package com.gradprj.erp.service;


import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import com.gradprj.erp.domain.TableInfo;
import com.gradprj.erp.mapper.PageInfo_Mapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageInfo_Service_Impl implements PageInfo_Service {

    @Autowired
    private PageInfo_Mapper pageInfo_mapper;

    @Autowired
    private CodeList_Service codeList_service;

    @Autowired
    private AddiPage_Service addiPage_service;

    @Autowired
    private PrintList_Service printList_service;


    @Override
    @Transactional
    public DefaultRes getPageInfo(String pagecode) {
        Map<String, Object> resultMap = new HashMap<>();
        String pagename = pageInfo_mapper.getPageName(pagecode);
        String table_name = pageInfo_mapper.getTableName(pagename);
        List<TableInfo> tableInfoList = pageInfo_mapper.gettableinfo(table_name);


        resultMap.put("pagename", pagename);
        resultMap.put("tablename", table_name);
        resultMap.put("data", tableInfoList);
        resultMap.put("key_column", pageInfo_mapper.getKeyColumn(table_name));
        resultMap.put("code", codeList_service.findByCodeTable(table_name));
        /*
            외래키 정보는 테이블의 컬럼명과 외래키가 참조하는 테이블의 컬럼명을 가져옴
         */
        for (TableInfo tableInfo : tableInfoList) {
            if (tableInfo.getRef_Table() != null) {
                Map<String,Object> refTableInfo = new HashMap<>();
                refTableInfo.put("info", pageInfo_mapper.gettableinfo(tableInfo.getRef_Table()));
                refTableInfo.put("data", pageInfo_mapper.getFKData(tableInfo.getRef_Table()));
                resultMap.put(tableInfo.getRef_Table(), refTableInfo);
            }
        }
        resultMap.put("print_info", printList_service.getPrintList(pagecode));
        resultMap.put("addi_page", addiPage_service.getAddipagelink(pagecode));

        if(resultMap.isEmpty()){
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.DB_ERROR);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessages.Data_found, resultMap);
    }
}
