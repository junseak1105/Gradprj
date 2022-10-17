package com.gradprj.erp.RestApi.controller;

import com.gradprj.erp.RestApi.domain.SM_ItemGroup_Reg;
import com.gradprj.erp.RestApi.service.SM_ItemGroup_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class SM_ItemGroup_RegController {

    @Autowired
    private SM_ItemGroup_Reg_Service sm_itemgroup_reg_service;

    @ApiOperation(value = "상품그룹 등록", notes = "상품그룹 등록")
    @PostMapping("/itemgroup")
    public ResponseEntity saveItemGroup(SM_ItemGroup_Reg sm_itemgroup_reg){
        return new ResponseEntity(sm_itemgroup_reg_service.save(sm_itemgroup_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "상품그룹 수정", notes = "상품그룹 수정")
    @PutMapping("/itemgroup")
    public ResponseEntity updateItemGroup(SM_ItemGroup_Reg sm_itemgroup_reg){
        return new ResponseEntity(sm_itemgroup_reg_service.update(sm_itemgroup_reg), HttpStatus.OK);
    }

    @ApiOperation(value = "상품그룹 삭제", notes = "상품그룹 삭제")
    @DeleteMapping("/itemgroup/{ItemGroup_Code}")
    public ResponseEntity deleteItemGroup(@PathVariable String ItemGroup_Code){
        return new ResponseEntity(sm_itemgroup_reg_service.delete(ItemGroup_Code), HttpStatus.OK);
    }

    @ApiOperation(value = "상품그룹 조회", notes = "상품그룹 조회")
    @GetMapping("/itemgroup/{ItemGroup_Code}")
    public ResponseEntity findItemGroup(@PathVariable String ItemGroup_Code){
        return new ResponseEntity(sm_itemgroup_reg_service.getItemGroupByItemGroupCode(ItemGroup_Code), HttpStatus.OK);
    }

    @ApiOperation(value = "상품그룹 전체 조회", notes = "상품그룹 전체 조회")
    @GetMapping("/itemgroup")
    public ResponseEntity findAllItemGroup(){
        return new ResponseEntity(sm_itemgroup_reg_service.findAll(), HttpStatus.OK);
    }


}
