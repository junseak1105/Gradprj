package com.gradprj.web.web.table;

import com.gradprj.web.TableApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@RequestMapping("/table-service")
public class TableServiceController {

    @GetMapping("/create")
    @ResponseBody
    public String create() throws SQLException {
        /*
        Table 형태
        {
            "Row" : "Row",
            "Name" : "Name",
            "Field" : "Field",
            "Type" : "Type",
            "Null" : "Null",
            "Key" : "Key",
            "Default" : "Default",
            "Extra" : "Extra"

         */

        TableApp tableApp = new TableApp();
//        tableApp.table_create(tableRepository);
        System.out.println("test");
        return "test";
    }
}
