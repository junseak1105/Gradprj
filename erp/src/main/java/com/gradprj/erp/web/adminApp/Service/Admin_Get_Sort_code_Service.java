package com.gradprj.erp.web.adminApp.Service;

import com.gradprj.erp.web.adminApp.DTO.SortcodeRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Admin_Get_Sort_code_Service implements Admin_Service {

    private SortcodeRepository sortcodeRepository;

    @Autowired
    public Admin_Get_Sort_code_Service(SortcodeRepository sortcodeRepository) {
        this.sortcodeRepository = sortcodeRepository;
    }

    @Override
    public String Execute(String condition) throws SQLException {
        ResultSet rs = db_service.DB_Ex_query("select * from sort_code " + condition);
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            String sort_lv1 = rs.getString("sort_lv1");
            String sort_lv2 = rs.getString("sort_lv2");
            String sort_code = rs.getString("sort_code");
            String sort_code_desc = rs.getString("sort_code_desc");
            jsonObject.put("sort_lv1", sort_lv1);
            jsonObject.put("sort_lv2", sort_lv2);
            jsonObject.put("sort_code", sort_code);
            jsonObject.put("sort_code_desc", sort_code_desc);
            result.add(jsonObject);
        }
        return result.toJSONString();
    }
}
