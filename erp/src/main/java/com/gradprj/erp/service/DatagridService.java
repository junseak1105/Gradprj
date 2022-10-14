package com.gradprj.erp.service;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface DatagridService {
    String getTableName(String pagename) throws SQLException;

    String deleteData(JSONObject data) throws SQLException;

    void getColumns(String table_name) throws SQLException;

    String getData(String pagename) throws SQLException;

    String saveData(JSONObject data) throws SQLException;
}
