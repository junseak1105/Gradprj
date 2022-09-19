package com.gradprj.erp.web.tableApp.service;


import com.gradprj.erp.web.tableApp.data.TableRepository;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface Table_Service{
    void newTable(JSONObject json) throws SQLException;
    void oldTable(String table) throws SQLException;

    TableRepository getTableRepository();
}
