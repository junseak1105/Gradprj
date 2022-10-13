package com.gradprj.erp.web.adminApp;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.BaseController;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * [Page] Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminAppController extends BaseApp {

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
            case "delete_page":

                result = adminApp.Page_delete(request.getParameter("idx"));
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
