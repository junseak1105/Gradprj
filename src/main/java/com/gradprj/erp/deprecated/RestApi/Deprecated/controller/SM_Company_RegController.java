package com.gradprj.erp.deprecated.RestApi.Deprecated.controller;

import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Company_Reg;
import com.gradprj.erp.deprecated.RestApi.Deprecated.service.SM_Company_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/data")
public class SM_Company_RegController {

    @Autowired
    private SM_Company_Reg_Service sm_company_reg_service;

    @ApiOperation(value = "회사정보 가져오기", notes = "동작방식 작성중")
    @GetMapping("/company")
    public ResponseEntity getCompany(){
        return new ResponseEntity(sm_company_reg_service.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "특정 회사정보 가져오기", notes = "동작방식 작성중")
    @GetMapping("/company/{Company_Code}")
    public ResponseEntity getCompany(@PathVariable String Company_Code){
        return new ResponseEntity(sm_company_reg_service.findByComRegNo(Company_Code), HttpStatus.OK);
    }

    @ApiOperation(value = "회사정보 등록", notes = "동작방식 작성중")
    @PostMapping("/company")
    public ResponseEntity postCompany(SM_Company_Reg sm_company_reg){
        return new ResponseEntity(sm_company_reg_service.save(sm_company_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "회사정보 수정", notes = "동작방식 작성중")
    @PutMapping("/company")
    public ResponseEntity putCompany(@RequestBody SM_Company_Reg sm_company_reg){
        return new ResponseEntity(sm_company_reg_service.save(sm_company_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "회사정보 삭제", notes = "동작방식 작성중")
    @DeleteMapping("/company/{Company_Code}")
    public ResponseEntity deleteCompany(@PathVariable String Company_Code){
        sm_company_reg_service.delete(Company_Code);
        return new ResponseEntity(HttpStatus.OK);
    }

}
