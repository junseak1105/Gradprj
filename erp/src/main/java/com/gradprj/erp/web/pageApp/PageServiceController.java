package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.BaseController;
import com.gradprj.erp.web.pageApp.DTO.Page;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * [Page] Controller
 */
@Controller
@RequestMapping("/page")
public class PageServiceController extends BaseController {

    /**
     * [페이지 이동 매핑] 시작
     * 1./datagrid/{viewname} : Datagrid 페이지 이동
     * 2./admin : 관리자 페이지 이동
     */

    /**
     * 1. /datagrid/{viewname} : Datagrid 페이지 이동
     */
    @RequestMapping("/datagrid/{urlid}")
    public ModelAndView page_create(@PathVariable("urlid") String urlid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/datagrid");
        return mv;
    }

    /**
     * 2. /admin : 관리자 페이지 이동
     */
    @RequestMapping("/admin")
    public ModelAndView page_admin() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/admin");
        return mv;
    }

    /**
     * [페이지 이동 매핑] 끝
     */


    /**
     * [API 매핑] 시작
     * 1. /service/get_page_list : 페이지 리스트 가져오기
     * 2. /service/get_page_category : 페이지의 데이터그리드 가져오기
     * 3. /service/name_dupchk : 페이지 이름, 분류명 중복 체크
     * 4. /service/create_page : 페이지 생성
     * 5. /service/get_datagrid : 데이터그리드 가져오기
     */

    /**
     * 1. /service/get_page_list : 페이지 리스트 가져오기
     */
    @RequestMapping("/service/get_page_list")
    @ResponseBody
    public String get_page_list() throws SQLException {
        JSONArray json = new JSONArray();
        ArrayList temp = pageApp.page_get_list();
        for (int i = 0; i < temp.size(); i++) {
            Page page = (Page) temp.get(i);
            JSONObject page_json = new JSONObject();
            page_json.put("idx", page.getIdx());
            page_json.put("page_name", page.getPage_name());
            page_json.put("page_desc", page.getPage_desc());
            page_json.put("page_url", page.getPage_url());
            page_json.put("page_table", page.getPage_table());
            page_json.put("page_category", page.getPage_category());

            json.put(page_json);
        }
//        System.out.println("get_page_list");
//        System.out.println(json.toString());
        return json.toString();
    }

    /**
     * 2. /service/get_page_category : 페이지의 데이터그리드 가져오기
     */
    @RequestMapping("/service/get_page_category")
    @ResponseBody
    public String get_page_category() throws SQLException {
        JSONArray json = new JSONArray();
        ArrayList temp = pageApp.page_get_category();

        for (int i = 0; i < temp.size(); i++) {
            Page page = (Page) temp.get(i);
            JSONObject page_json = new JSONObject();
            page_json.put("idx", page.getIdx());
            page_json.put("page_category", page.getPage_category());
            page_json.put("page_url", page.getPage_url());

            json.put(page_json);
        }

        return json.toString();
    }


    /**
     * 3. /service/name_dupchk : 페이지 이름, 분류명 중복 체크
     */
    //미완성, 페이지명, 카테고리 분류 중복체크로 구현해야함
    @RequestMapping("/service/name_dupchk")
    @ResponseBody
    public String name_dupchk(@RequestBody JSONObject json) throws SQLException {
        JSONObject result = pageApp.Page_name_dupchk(json);
        System.out.println(result.toJSONString());
        return result.toJSONString();
    }

    /**
     * 4. /service/create_pagelist : 페이지 생성
     */
    @RequestMapping("/service/create_page")
    @ResponseBody
    public String create_page(@RequestBody JSONObject json) throws SQLException {
        JSONObject result = pageApp.Page_create(json);
        return result.toJSONString();
    }


    /**
     * 5. /service/delete_pagelist : 페이지 삭제
     */

    /**
    * [API 매핑] 끝
    */
//    @RequestMapping("/create/{urlid}")
//    public ModelAndView page_create(@PathVariable("urlid") String urlid){
//        ModelAndView mv = new ModelAndView();
//        List<String> listTest = new ArrayList<String>();
//        listTest.add("test1");
//        listTest.add("test2");
//        listTest.add("test3");
//
//        mv.addObject("listTest",listTest);
//        // jstl로 호출
//        mv.addObject("ObjectTest","테스트입니다."); // jstl로 호출
//        mv.setViewName("page/test"); // 실제 호출될 /WEB-INF/jsp/model/test// Mv.jspreturn mv;
//
//        return mv;
//    }

}
