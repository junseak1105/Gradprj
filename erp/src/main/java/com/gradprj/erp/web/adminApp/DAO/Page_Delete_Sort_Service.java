package com.gradprj.erp.web.adminApp.DAO;

import com.gradprj.erp.web.adminApp.DTO.SortcodeRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseController.db_service;

public class Page_Delete_Sort_Service implements Page_Service {

    private SortcodeRepository sortcodeRepository;

    @Autowired
    public Page_Delete_Sort_Service(SortcodeRepository sortcodeRepository) {
        this.sortcodeRepository = sortcodeRepository;
    }

    @Override
    public String Execute(String condition) throws SQLException {
        JSONObject result = new JSONObject();
        System.out.println(condition);
        if(db_service.DB_Ex_query_nr(condition)) {
            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result.toJSONString();
    }
}
