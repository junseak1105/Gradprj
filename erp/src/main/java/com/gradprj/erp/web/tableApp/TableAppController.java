package com.gradprj.erp.web.tableApp;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.BaseController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/table")
public class TableAppController extends BaseApp {

    @RequestMapping("/{urlid}")
    public ModelAndView page_admin(@PathVariable("urlid") String urlid){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("table/"+urlid);
        return mv;
    }

    @RequestMapping("/service/create")
    @ResponseBody
    public String create(@RequestBody JSONObject json) throws SQLException {
        tableApp.table_create(json);
        return "";
    }

    @PostMapping("/service/name_dupchk")
    @ResponseBody
    public String name_dupchk(@RequestBody JSONObject json) throws SQLException {
        JSONObject result = tableApp.table_name_dupchk(json);
        System.out.println(result.toJSONString());
        return result.toJSONString();
    }

    @GetMapping("/service/get_list")
    @ResponseBody
    public String get_list() throws SQLException {
        JSONArray json = tableApp.table_get_list();
        return json.toString();
    }

    @GetMapping("/service/get_info")
    @ResponseBody
    public String get_info(@RequestParam String name) throws SQLException {
        JSONObject json = tableApp.table_get_info(name);
        return json.toJSONString();
    }

}
