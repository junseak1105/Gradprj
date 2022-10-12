package com.gradprj.erp.web.pageApp.DTO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.jsp.tagext.PageData;
import java.util.ArrayList;
import java.util.Map;

public interface PageDataRepository {
    JSONObject findAll();
    void save(String key, JSONArray value);
}
