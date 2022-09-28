package com.gradprj.erp.web.tableApp.DTO;

import java.util.ArrayList;
import java.util.Map;

public interface TableRepository {

    void save(Table table);
    Table findByField(String field);
    Map findAll();

    String getTableName();

    String getTableComment();

    ArrayList<String> getRownames();

    void deleteAll();
}
