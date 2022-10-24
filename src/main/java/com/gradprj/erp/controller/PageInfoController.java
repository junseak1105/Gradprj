package com.gradprj.erp.controller;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.service.PageInfo_Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class PageInfoController {

    @Autowired
    private PageInfo_Service PageInfoService;

    @GetMapping("/pageinfo/{pagecode}")
    @ApiOperation(value = "페이지 정보 조회", notes = "페이지 코드를 입력하면 페이지 생성을 위한 정보가 반환됨\n" +
            "[반환내용]\n" +
            "pagename: 페이지 이름\n" +
            "tablename: 사용 테이블 명\n" +
            "data: 테이블 정보\n" +
            "key_column: 사용 테이블 식별자 컬럼\n" +
            "code: 사용 테이블의 코드 정보(없을시 null)\n" +
            "외래키 데이터 목록:\n" +
            "  - info: 외래키 테이블 정보\n" +
            "  - data: 외래키 테이블 내부 데이터\n" +
            "print_info: 출력 서식 정보\n" +
            "addi_page: 추가 기능 링크\n" +
            ""
    )
    public DefaultRes getTableInfo(@PathVariable String pagecode){
        return PageInfoService.getPageInfo(pagecode);
    }

}
