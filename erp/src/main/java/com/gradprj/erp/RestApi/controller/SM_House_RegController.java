package com.gradprj.erp.RestApi.controller;

import com.gradprj.erp.RestApi.domain.SM_House_Reg;
import com.gradprj.erp.RestApi.service.SM_House_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class SM_House_RegController {

    @Autowired
    private SM_House_Reg_Service sm_house_reg_service;

    @ApiOperation(value = "창고 등록", notes = "창고 등록")
    @PostMapping("/house")
    public ResponseEntity saveHouse(SM_House_Reg sm_house_reg){
        return new ResponseEntity(sm_house_reg_service.save(sm_house_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "창고 수정", notes = "창고 수정")
    @PutMapping("/house")
    public ResponseEntity updateHouse(SM_House_Reg sm_house_reg){
        return new ResponseEntity(sm_house_reg_service.update(sm_house_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "창고 삭제", notes = "창고 삭제")
    @DeleteMapping("/house/{House_Code}")
    public ResponseEntity deleteHouse(@PathVariable String House_Code){
        return new ResponseEntity(sm_house_reg_service.delete(House_Code), HttpStatus.OK);
    }

    @ApiOperation(value = "창고 조회", notes = "창고 조회")
    @GetMapping("/house/{House_Code}")
    public ResponseEntity findHouse(@PathVariable String House_Code){
        return new ResponseEntity(sm_house_reg_service.getHouseByHouseCode(House_Code), HttpStatus.OK);
    }

    @ApiOperation(value = "창고 전체 조회", notes = "창고 전체 조회")
    @GetMapping("/house")
    public ResponseEntity findAllHouse(){
        return new ResponseEntity(sm_house_reg_service.findAll(), HttpStatus.OK);
    }
}
