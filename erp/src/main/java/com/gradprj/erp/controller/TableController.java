package com.gradprj.erp.controller;

import com.gradprj.erp.service.TableService;
import com.gradprj.erp.service.TableServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/api/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @ApiOperation(value = "테이블 생성창 호출", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/new")
    public String newTable(){
        return "table/table";
    }

    @ApiOperation(value = "테이블 생성", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/createTable")
    public void getData(@RequestBody JSONObject json) throws SQLException {
        tableService.createTable(json);
    }

    @ApiOperation(value = "테이블 삭제", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/dropTable")
    public void dropTable(@RequestBody JSONObject json) throws SQLException {
        tableService.dropTable();
    }

    @ApiOperation(value = "테이블명 중복 확인", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping("/nameDupchk")
    @ResponseBody
    public String nameDupchk(@RequestBody JSONObject json) throws SQLException {
        return tableService.nameDupchk(json);
    }

    @ApiOperation(value = "테이블목록 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getTableList")
    @ResponseBody
    public String getTableList() throws SQLException {
        return tableService.getTableList();
    }

    @ApiOperation(value = "테이블정보 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getTableInfo")
    @ResponseBody
    public String getTableInfo() throws SQLException {
        return tableService.getTableInfo();
    }

    @ApiOperation(value = "테이블컬럼 가져오기", notes = "동작방식 작성중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/getTableColumns")
    public void getTableColumns() throws SQLException {
        tableService.getTableColumns();
    }


}
