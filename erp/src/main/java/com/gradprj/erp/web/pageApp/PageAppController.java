package com.gradprj.erp.web.pageApp;

import com.gradprj.erp.BaseApp;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
@Log4j2
@Controller
@RequestMapping("/page")
public class PageAppController extends BaseApp {


    @RequestMapping("/{path}")
    public String pageApp(@PathVariable String path, HttpServletRequest request) throws SQLException {
        return "page/" + path;
    }
    @RequestMapping("/service/{path}")
    @ResponseBody
    public String PageService(@PathVariable String path, HttpServletRequest request) throws SQLException {
        log.info("현재경로: "+request.getRequestURI());
        switch (path) {
            case "navbar":
                return null;//pageApp.Navbar();
            case "datagrid":
                return pageApp.GetPage(path,request.getParameter("pagename"));
            case "page":
                return pageApp.GetPage(path,request.getParameter("pagename"));
            default:
                return "error";
        }
    }

    @RequestMapping("/service/edit/{path}")
    @ResponseBody
    public String PageService(@PathVariable String path, @RequestBody JSONObject json) throws Exception {
        switch (path) {
            case "save":
                return pageApp.SaveData(json);
            case "delete":
                return pageApp.DeleteData(json);
            default:
                return "error";
        }
    }

}
