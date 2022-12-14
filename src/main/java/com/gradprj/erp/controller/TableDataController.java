package com.gradprj.erp.controller;

import com.gradprj.erp.service.TableData_Service;
import com.gradprj.erp.service.TableData_Service_Impl;
import com.gradprj.erp.config.DefaultRes;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log4j2
public class TableDataController {

    @Autowired
    private TableData_Service tableData_service;

    @ApiOperation(value = "테이블 데이터 조회", notes = "테이블 데이터 조회")
    @GetMapping("/tabledata/{table_name}")
    @ResponseBody
    public DefaultRes getTableData(@PathVariable String table_name){
        return tableData_service.getAllData(table_name);
    }

    //조건 검색 미작성
//    @ApiOperation(value = "테이블 데이터 조회", notes = "테이블 데이터 조회")
//    @PostMapping("/get/{table_name}")
//    @ResponseBody
//    public ResponseEntity getTableData(@PathVariable String table_name, @PathVariable String key, @PathVariable String value, @PathVariable String order){
//        return new ResponseEntity(tableData_service.getData(table_name, key, value, order), HttpStatus.OK);
//    }

    @PostMapping("/tabledata/{table_name}")
    @ResponseBody
    public DefaultRes saveTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        System.out.println("testestests");
        return tableData_service.saveData(table_name, (String) data.get("column"), (String) data.get("value"));
    }

    @PutMapping("/tabledata/{table_name}")
    @ResponseBody
    public DefaultRes updateTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        return tableData_service.updateData(table_name, (String) data.get("column"), (String) data.get("value"),(String)data.get("key_column"),(String)data.get("key_value"));
    }

    @DeleteMapping("/tabledata/{table_name}")
    @ResponseBody
    public DefaultRes deleteTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        return tableData_service.deleteData(table_name, (String) data.get("key_column"), (String) data.get("selected"));
    }

}
