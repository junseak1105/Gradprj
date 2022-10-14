package com.gradprj.erp.service;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface TableService {
    void createTable(JSONObject json);

    void dropTable();

    String getTableList() throws SQLException;

    String getTableInfo() throws SQLException;

    void getTableColumns();

    String nameDupchk(JSONObject json) throws SQLException;

}
