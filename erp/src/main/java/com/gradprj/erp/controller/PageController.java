package com.gradprj.erp.controller;


import com.gradprj.erp.service.PageService;
import com.gradprj.erp.service.PageServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("api/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @ApiOperation(value = "페이지 추가", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/addPage")
    @ResponseBody
    public String getData(@RequestBody JSONObject json) throws SQLException {
        return pageService.addPage(json);
    }

    @ApiOperation(value = "페이지카테고리 제거", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/deletePageCategory")
    @ResponseBody
    public String deletePageCategory(@RequestBody JSONObject json) throws SQLException {
        return pageService.deletePageCategory(json.get("idx").toString());
    }

    @ApiOperation(value = "페이지 제거", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/deletePage")
    @ResponseBody
    public String deletePage(@RequestBody JSONObject json) throws SQLException {
        return pageService.deletePage(json.get("idx").toString());
    }

    @ApiOperation(value = "페이지 수정", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/updatePage")
    @ResponseBody
    public String updatePage(@RequestBody JSONObject json) throws SQLException {
        return pageService.updatePage(); //미구현
    }


    @ApiOperation(value = "페이지 카테고리 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getPageCategory")
    @ResponseBody
    public String PageCategory() throws SQLException {
        return pageService.getPageCategory();
    }

    @ApiOperation(value = "페이지 목록 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getPageList")
    @ResponseBody
    public String PageList() throws SQLException {
        return pageService.getPageList();
    }
}
