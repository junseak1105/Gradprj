package com.gradprj.erp.deprecated.RestApi.Deprecated.controller;

import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Zipcode;
import com.gradprj.erp.deprecated.RestApi.Deprecated.service.SM_Zipcode_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/data")
public class SM_ZipCodeController {

    @Autowired
    private SM_Zipcode_Service sm_zipcode_service;

    @ApiOperation(value = "우편번호 가져오기", notes = "동작방식 작성중")
    @GetMapping("/zip")
    public ResponseEntity getZip(){
        return new ResponseEntity(sm_zipcode_service.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "특정 우편번호 조회", notes = "동작방식 작성중")
    @GetMapping("/zip/{ZipCode}")
    public ResponseEntity getZipByZipCode(@PathVariable String ZipCode){
        System.out.println(ZipCode);
        return new ResponseEntity(sm_zipcode_service.getZipByZipCode(ZipCode), HttpStatus.OK);
    }

    @ApiOperation(value = "우편번호 저장", notes = "동작방식 작성중")
    @PostMapping("/zip")
    public ResponseEntity saveZip(SM_Zipcode sm_zipcode){
        return new ResponseEntity(sm_zipcode_service.save(sm_zipcode), HttpStatus.OK);
    }

    @ApiOperation(value = "우편번호 수정", notes = "동작방식 작성중")
    @PutMapping("/zip")
    public ResponseEntity updateZip(SM_Zipcode sm_zipcode){
        return new ResponseEntity(sm_zipcode_service.update(sm_zipcode), HttpStatus.OK);
    }

    @ApiOperation(value = "우편번호 삭제", notes = "동작방식 작성중")
    @DeleteMapping("/zip/{ZipCode}")
    public ResponseEntity deleteZip(@PathVariable String ZipCode){
        return new ResponseEntity(sm_zipcode_service.delete(ZipCode), HttpStatus.OK);
    }
}
