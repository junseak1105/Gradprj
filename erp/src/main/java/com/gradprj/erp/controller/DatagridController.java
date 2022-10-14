package com.gradprj.erp.controller;

import com.gradprj.erp.service.DatagridService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/api/datagrid")
public class DatagridController {

    @Autowired
    private DatagridService datagridService;


    @ApiOperation(value = "Datagrid의 데이터 삭제", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/deleteData")
    @ResponseBody
    public String deleteData(@RequestBody JSONObject json) throws SQLException {
        return datagridService.deleteData(json);
    }

    @ApiOperation(value = "Datagrid의 데이터 저장", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/saveData")
    @ResponseBody
    public String saveData(@RequestBody JSONObject json) throws SQLException {
        return datagridService.saveData(json);
    }

    @ApiOperation(value = "Datagrid정보 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getData")
    @ResponseBody
    public String getData(@RequestParam String pagename) throws SQLException {
        return datagridService.getData(pagename);
    }

}
