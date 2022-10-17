package com.gradprj.erp.RestApi.controller;

import com.gradprj.erp.RestApi.domain.SM_Employee_Reg;
import com.gradprj.erp.RestApi.service.SM_Employee_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class SM_Employee_RegController {

    @Autowired
    private SM_Employee_Reg_Service sm_employee_reg_service;


    @ApiOperation(value = "직원 등록", notes = "직원 등록")
    @PostMapping("/employee")
    public ResponseEntity saveEmployee(SM_Employee_Reg sm_employee_reg){
        return ResponseEntity.ok(sm_employee_reg_service.save(sm_employee_reg));
    }

    @ApiOperation(value = "직원 수정", notes = "직원 수정")
    @PutMapping("/employee")
    public ResponseEntity updateEmployee(SM_Employee_Reg sm_employee_reg){
        return ResponseEntity.ok(sm_employee_reg_service.update(sm_employee_reg));
    }

    @ApiOperation(value = "직원 삭제", notes = "직원 삭제")
    @DeleteMapping("/employee/{Employee_Code}")
    public ResponseEntity deleteEmployee(@PathVariable String Employee_Code){
        return ResponseEntity.ok(sm_employee_reg_service.delete(Employee_Code));
    }

    @ApiOperation(value = "직원 조회", notes = "직원 조회")
    @GetMapping("/employee/{Employee_Code}")
    public ResponseEntity findEmployee(@PathVariable String Employee_Code){
        return ResponseEntity.ok(sm_employee_reg_service.findByEmployee_Code(Employee_Code));
    }

    @ApiOperation(value = "직원 전체 조회", notes = "직원 전체 조회")
    @GetMapping("/employee")
    public ResponseEntity findAllEmployee(){
        return ResponseEntity.ok(sm_employee_reg_service.findAll());
    }
}
