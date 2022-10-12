package com.gradprj.erp.web.adminApp.Service;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DTO.PageRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Admin_Get_Category_Service extends BaseApp implements Admin_Service {

    private final PageRepository pageRepository;

    @Autowired
    public Admin_Get_Category_Service(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public String Execute(String condition) throws SQLException {
        JSONArray json = new JSONArray();
        ResultSet rs = db_service.DB_Ex_query("select * from page_category");
        while (rs.next()) {
            JSONObject page_json = new JSONObject();
            page_json.put("idx", Integer.parseInt(rs.getString("idx")));
            page_json.put("page_url", rs.getString("base_url"));
            page_json.put("page_category", rs.getString("page_category"));
            json.put(page_json);
        }
        return json.toString();
    }
}
