package com.gradprj.erp.web.adminApp;

import com.gradprj.erp.BaseController;
import com.gradprj.erp.web.adminApp.DAO.Page_Get_Sort_lv1_Service;
import com.gradprj.erp.web.adminApp.DAO.Page_Name_Dupchk_Service;
import com.gradprj.erp.web.adminApp.DTO.Page;
import com.gradprj.erp.web.adminApp.DTO.Sortcode;
import com.gradprj.erp.web.adminApp.DTO.SortcodeRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * [Page] Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminServiceController extends BaseController {

    /**
     * [페이지 이동 매핑] 시작
     * 1./admin : 관리자 페이지 이동
     */

    /**
     * 1. /admin : 관리자 페이지 이동
     */
    @RequestMapping("/{urlid}")
    public ModelAndView page_admin(@PathVariable("urlid") String urlid) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/" + urlid);
        return mv;
    }

    /**
     * [페이지 이동 매핑] 끝
     */


    /**
     * [API 매핑] 단방향 전송
     */
    @RequestMapping("/service/simplex/{urlid}")
    @ResponseBody
    public String service_admin_simplex(@PathVariable("urlid") String urlid, HttpServletRequest request) throws SQLException {
        String result = "";
        System.out.println("urlid : " + urlid);
        switch (urlid) {
            case "get_page_list":
                result = adminApp.page_get_list();
                break;
            case "get_page_category":
                result = adminApp.page_get_category();
                break;
            case "get_sort_lv1":
                result = adminApp.page_get_sort_lv1();
                break;
            case "get_sort_lv2":
                result = adminApp.page_get_sort_lv2(request.getParameter("sort_lv1"));
                break;
            case "get_sort_code":
                result = adminApp.page_get_sort_code(request.getParameter("sort_lv1"), request.getParameter("sort_lv2"));
                break;
            case "delete_sort_lv1":
                result = adminApp.page_delete_sort(request.getParameter("sort_lv1"));
                break;
            case "delete_sort_lv2":
                result = adminApp.page_delete_sort(request.getParameter("sort_lv1"),request.getParameter("sort_lv2"));
                break;
            case "delete_sort_code":
                result = adminApp.page_delete_sort(request.getParameter("sort_lv1"),request.getParameter("sort_lv2"),request.getParameter("sort_code"));
                break;
        }
        return result;
    }

    /**
     * [API 매핑] 단방향 전송 끝
     * ==============================================
     */

    /**
     * [API 매핑] 양방향 전송(json)
     */

    @RequestMapping("/service/duplex/{urlid}")
    @ResponseBody
    public String service_admin_duplex(@PathVariable("urlid") String urlid, @RequestBody JSONObject json) throws SQLException {
        String result = "";
        System.out.println("urlid : " + urlid);
        switch (urlid) {
            case "name_dupchk":
                result = adminApp.Page_name_dupchk(json);
                break;
            case "create_page":
                result = adminApp.Page_create(json);
                break;
        }
        return result;
    }


    /**
     * [API 매핑] 양방향 전송(json) 끝
     */

}
