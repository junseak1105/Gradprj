package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.BaseController;
import com.gradprj.erp.web.pageApp.DTO.Page;
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


@Controller
@RequestMapping("/page")
public class PageServiceController extends BaseController {

    @RequestMapping("/datagrid/{urlid}")
    public ModelAndView page_create(@PathVariable("urlid") String urlid){
        ModelAndView mv = new ModelAndView();


        mv.setViewName("page/datagrid");
        return mv;
    }

    /**
     * 페이지 관리 어드민 페이지
     * @return
     */
    @RequestMapping("/admin")
    public ModelAndView page_admin() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/admin");
        return mv;
    }

    @RequestMapping("/service/get_page_list")
    @ResponseBody
    public String get_page_list() throws SQLException {
        JSONObject json = new JSONObject();
        ArrayList temp = pageApp.page_get_list();
        for(int i=0; i<temp.size(); i++){
            Page page = (Page)temp.get(i);
            JSONObject page_json = new JSONObject();
            page_json.put("name", page.getPage_name());
            page_json.put("urlid", page.getPage_desc());
            page_json.put("description", page.getPage_desc());
            page_json.put("description", page.getPage_table());

            json.put(i, page_json);
        }
        System.out.println("get_page_list");
        return json.toJSONString();
    }

    @RequestMapping("/service/name_dupchk")
    @ResponseBody
    public String name_dupchk(@RequestBody JSONObject json) throws SQLException {
        JSONObject result = pageApp.Page_name_dupchk(json);
        System.out.println(result.toJSONString());
        return result.toJSONString();
    }


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
