package com.gradprj.erp.controller;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.service.TableInfo_Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class TableInfoController {

    @Autowired
    private TableInfo_Service tableInfoService;

    @GetMapping("/tableinfo/{page_name}")
    @ApiOperation(value = "테이블 정보 조회", notes = "페이지 명을 입력하면 페이지 생성을 위한 정보가 반환됨")
    public DefaultRes getTableInfo(@PathVariable String page_name){
        return tableInfoService.getTableInfo(page_name);
    }

}
