package com.gradprj.erp.RestApi.Deprecated.controller;

import com.gradprj.erp.RestApi.Deprecated.domain.SM_Department_Reg;
import com.gradprj.erp.RestApi.Deprecated.service.SM_Department_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class SM_Department_RegController {

    @Autowired
    private SM_Department_Reg_Service sm_department_reg_service;

    @ApiOperation(value = "부서정보 가져오기", notes = "동작방식 작성중")
    @GetMapping("/department")
    public ResponseEntity getDepartment(){
        return new ResponseEntity(sm_department_reg_service.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "특정 부서정보 가져오기", notes = "동작방식 작성중")
    @GetMapping("/department/{Department_Code}")
    public ResponseEntity getDepartment(@PathVariable String Department_Code){
        return new ResponseEntity(sm_department_reg_service.findByDepartment_Code(Department_Code), HttpStatus.OK);
    }

    @ApiOperation(value ="부서정보 등록", notes = "동작방식 작성중")
    @PostMapping("/department")
    public ResponseEntity postDepartment(SM_Department_Reg sm_department_reg){
        return new ResponseEntity(sm_department_reg_service.save(sm_department_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "부서정보 수정", notes = "동작방식 작성중")
    @PutMapping("/department")
    public ResponseEntity putDepartment(SM_Department_Reg sm_department_reg){
        return new ResponseEntity(sm_department_reg_service.update(sm_department_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "부서정보 삭제", notes = "동작방식 작성중")
    @DeleteMapping("/department/{Department_Code}")
    public ResponseEntity deleteDepartment(@PathVariable String Department_Code){
        return new ResponseEntity(sm_department_reg_service.delete(Department_Code), HttpStatus.OK);
    }

}
