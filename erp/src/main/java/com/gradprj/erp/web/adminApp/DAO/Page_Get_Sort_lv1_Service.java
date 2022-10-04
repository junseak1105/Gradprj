package com.gradprj.erp.web.adminApp.DAO;

import com.gradprj.erp.web.adminApp.DTO.Sortcode;
import com.gradprj.erp.web.adminApp.DTO.SortcodeRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Page_Get_Sort_lv1_Service implements Page_Service {

    private SortcodeRepository sortcodeRepository;

    @Autowired
    public Page_Get_Sort_lv1_Service(SortcodeRepository sortcodeRepository) {
        this.sortcodeRepository = sortcodeRepository;
    }

    @Override
    public String Execute(String condition) throws SQLException {
        ResultSet rs = db_service.DB_Ex_query("select * from sort_lv1");
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            String sort_lv1 =rs.getString("sort_lv1");
            String sort_lv1_desc = rs.getString("sort_lv1_desc");
            jsonObject.put("sort_lv1",sort_lv1);
            jsonObject.put("sort_lv1_desc",sort_lv1_desc);
            result.add(jsonObject);
        }
        return result.toJSONString();
    }
}