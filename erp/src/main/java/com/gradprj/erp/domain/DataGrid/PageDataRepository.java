package com.gradprj.erp.domain.DataGrid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.jsp.tagext.PageData;
import java.util.ArrayList;
import java.util.Map;

@Component
public interface PageDataRepository {
    JSONObject findAll();
    void save(String key, JSONArray value);
}
