package com.gradprj.erp.RestApi.controller;

import com.gradprj.erp.RestApi.service.TableData_Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
@Log4j2
public class TableDataController {

    @Autowired
    private TableData_Service tableData_service;

    @ApiOperation(value = "테이블 데이터 조회", notes = "테이블 데이터 조회")
    @GetMapping("/get/{table_name}")
    @ResponseBody
    public ResponseEntity getTableData(@PathVariable String table_name){
        return new ResponseEntity(tableData_service.getAllData(table_name), HttpStatus.OK);
    }

    @ApiOperation(value = "테이블 데이터 조회", notes = "테이블 데이터 조회")
    @PostMapping("/get/{table_name}")
    @ResponseBody
    public ResponseEntity getTableData(@PathVariable String table_name, @PathVariable String key, @PathVariable String value, @PathVariable String order){
        return new ResponseEntity(tableData_service.getData(table_name, key, value, order), HttpStatus.OK);
    }

    @PostMapping("/save/{table_name}")
    @ResponseBody
    public ResponseEntity saveTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        return new ResponseEntity(tableData_service.saveData(table_name, (String) data.get("column"), (String) data.get("value")), HttpStatus.OK);
    }

    @PutMapping("/update/{table_name}")
    @ResponseBody
    public ResponseEntity updateTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        return new ResponseEntity(tableData_service.updateData(table_name, (String) data.get("column"), (String) data.get("value"),(String)data.get("key_column"),(String)data.get("key_value")), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{table_name}")
    @ResponseBody
    public ResponseEntity deleteTableData(@PathVariable String table_name, @RequestBody JSONObject data){
        return new ResponseEntity(tableData_service.deleteData(table_name, (String) data.get("key_column"), (String) data.get("selected")), HttpStatus.OK);
    }

}
