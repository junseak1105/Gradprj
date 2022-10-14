package com.gradprj.erp.service;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface PageService {
    String addPage(JSONObject json);
    public String deletePage(String idx);
    String deletePageCategory(String idx);
    String getPageCategory() throws SQLException;
    String getPageList() throws SQLException;
    String updatePage();
}
