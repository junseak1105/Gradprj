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


    @ApiOperation(value = "페이지 정보 조회", notes = "페이지 코드를 입력하면 페이지 생성을 위한 정보가 반환됨" +
            "[반환내용]" +
            "pagename: 페이지 이름" +
            "tablename: 사용 테이블 명" +
            "data: 테이블 정보" +
            "key_column: 사용 테이블 식별자 컬럼" +
            "code: 사용 테이블의 코드 정보(없을시 null)" +
            "외래키 데이터 목록:" +
            "  - info: 외래키 테이블 정보" +
            "  - data: 외래키 테이블 내부 데이터" +
            "print_info: 출력 서식 정보" +
            "addi_page: 추가 기능 링크" +
            ")"
    )
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
