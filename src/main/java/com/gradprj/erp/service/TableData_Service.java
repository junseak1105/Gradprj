package com.gradprj.erp.service;

import com.gradprj.erp.config.DefaultRes;

public interface TableData_Service {

    DefaultRes deleteData(String table_name, String key_column, String selected);

    DefaultRes saveData(String table_name, String column, String value);

    DefaultRes updateData(String tablename, String column, String value, String key_column, String key_value);

    DefaultRes getAllData(String table_name);

    DefaultRes getData(String table_name, String key, String value, String order);
}
