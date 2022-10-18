package com.gradprj.erp.RestApi.Deprecated.controller;

import com.gradprj.erp.RestApi.Deprecated.domain.SM_Item_Reg;
import com.gradprj.erp.RestApi.Deprecated.service.SM_Item_Reg_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class SM_Item_RegController {

    @Autowired
    private SM_Item_Reg_Service sm_item_reg_service;

    @ApiOperation(value = "상품등록", notes = "상품등록")
    @PostMapping("/item")
    public ResponseEntity saveItem(SM_Item_Reg sm_item_reg){
        return new ResponseEntity(sm_item_reg_service.save(sm_item_reg), HttpStatus.OK);
    }


    @ApiOperation(value = "상품수정", notes = "상품수정")
    @PutMapping("/item")
    public ResponseEntity updateItem(SM_Item_Reg sm_item_reg){
        return new ResponseEntity(sm_item_reg_service.update(sm_item_reg), HttpStatus.OK);
    }


    @ApiOperation(value = "상품삭제", notes = "상품삭제")
    @DeleteMapping("/item/{Item_Code}")
    public ResponseEntity deleteItem(@PathVariable String ItemCode){
        return new ResponseEntity(sm_item_reg_service.delete(ItemCode), HttpStatus.OK);
    }

    @ApiOperation(value = "상품조회", notes = "상품조회")
    @GetMapping("/item/{ItemCode}")
    public ResponseEntity getItem(@PathVariable String ItemCode){
        return new ResponseEntity(sm_item_reg_service.getItemByItemCode(ItemCode), HttpStatus.OK);
    }

    @ApiOperation(value = "상품목록조회", notes = "상품목록조회")
    @GetMapping("/list")
    public ResponseEntity getItemList(){
        return new ResponseEntity(sm_item_reg_service.findAll(), HttpStatus.OK);
    }
}
