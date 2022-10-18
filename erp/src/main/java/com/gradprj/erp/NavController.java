package com.gradprj.erp;


import com.gradprj.erp.RestApi.domain.TableCriteria;
import com.gradprj.erp.RestApi.domain.TableInfo;
import com.gradprj.erp.RestApi.service.TableData_Service;
import com.gradprj.erp.RestApi.service.TableInfo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NavController {

    @Autowired
    private TableData_Service tableData_service;

    @Autowired
    private TableInfo_Service tableInfo_service;

    @GetMapping("/list/{tablename}")
    public ModelAndView list(@PathVariable String tablename, TableCriteria cri) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("tablename", tablename);
        mv.addObject("key_column", tableInfo_service.getKeyColumn(tablename));
        mv.addObject("tabledata", tableData_service.getAllData(tablename));
        mv.addObject("tableinfo", tableInfo_service.getTableInfo(tablename));
        mv.setViewName("list");
        return mv;
    }


}
