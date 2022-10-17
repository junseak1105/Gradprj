package com.gradprj.erp;


import com.gradprj.erp.RestApi.service.TableData_Service;
import com.gradprj.erp.RestApi.service.TableInfo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TempController {

    @Autowired
    private TableData_Service tableData_service;

    @Autowired
    private TableInfo_Service tableInfo_service;

    @GetMapping("/list/{tablename}")
    public ModelAndView list(@PathVariable String tablename){
        ModelAndView mv = new ModelAndView();
        mv.addObject("tablename", tablename);
        mv.addObject("tabledata", tableData_service.getAllData(tablename));
        mv.addObject("tableinfo", tableInfo_service.getTableInfo(tablename));
        mv.setViewName("list");
        return mv;
    }
}
