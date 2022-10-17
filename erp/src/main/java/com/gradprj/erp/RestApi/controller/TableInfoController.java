package com.gradprj.erp.RestApi.controller;

import com.gradprj.erp.RestApi.service.TableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class TableInfoController {

    @Autowired
    private TableInfoService tableInfoService;

    @GetMapping("/tableinfo/{table_name}")
    public ResponseEntity getTableInfo(@PathVariable String table_name){
        return ResponseEntity.ok(tableInfoService.getTableInfo(table_name));
    }

}
