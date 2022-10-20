package com.gradprj.erp.controller;


import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import com.gradprj.erp.service.PageCategory_Service;
import com.gradprj.erp.service.PageList_Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class NavBarController {

    @Autowired
    private PageCategory_Service pageCategory_service;
    @Autowired
    private PageList_Service pageList_service;

    @ApiOperation(value = "네비게이션 바 데이터 조회", notes = "네비게이션 바 데이터 조회")
    @GetMapping("/api/navbar/{service}")
    @ResponseBody
    public DefaultRes getNavBar(@PathVariable String service){
        switch (service){
            case "pageCategory":
                return pageCategory_service.findAll();
            case "pageList":
                return pageList_service.findAll();
            default:
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessages.BAD_REQUEST);
        }

    }

}
