package com.gradprj.erp.deprecated.controller;

import com.gradprj.erp.deprecated.service.SortCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("api/sortcode")
public class SortCodeController {

    @Autowired
    private SortCodeService sortCodeService;

    @ApiOperation(value = "분류코드 저장", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/saveSortCode")
    @ResponseBody
    public String getData(@RequestBody JSONObject json) throws SQLException {
        return sortCodeService.saveSortCode(); //미구현
    }

    @ApiOperation(value = "대분류 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getSortLv1")
    @ResponseBody
    public String getSortLv1() throws SQLException {
        return sortCodeService.getSortLv1();
    }

    @ApiOperation(value = "중분류 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getSortLv2")
    @ResponseBody
    public String getSortLv2(@RequestParam String sort_lv1) throws SQLException {
        return sortCodeService.getSortLv2(sort_lv1);
    }

    @ApiOperation(value = "분류코드 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getSortCode")
    @ResponseBody
    public String getSortCode(@RequestParam String sort_lv1, @RequestParam String sort_lv2) throws SQLException {
        return sortCodeService.getSortCode(sort_lv1, sort_lv2);
    }

    @ApiOperation(value = "대분류 삭제", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/deleteSortLv1")
    @ResponseBody
    public String deleteSortLv1(@RequestParam String sort_lv1) throws SQLException {
        return sortCodeService.deleteSortLv1(sort_lv1);
    }

    @ApiOperation(value = "중분류 삭제", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/deleteSortLv2")
    @ResponseBody
    public String deleteSortLv2(@RequestParam String sort_lv1,@RequestParam String sort_lv2) throws SQLException {
        return sortCodeService.deleteSortLv2(sort_lv1,sort_lv2);
    }

    @ApiOperation(value = "분류코드 삭제", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/deleteSortCode")
    @ResponseBody
    public String deleteSortCode(@RequestParam String sort_lv1,@RequestParam String sort_lv2,@RequestParam String sort_code) throws SQLException {
        return sortCodeService.deleteSortCode(sort_lv1,sort_lv2,sort_code);
    }

}
