package com.jhk.Spring.web.table;

import com.jhk.Spring.web.DBService.DB_Service;

import java.sql.SQLException;

public interface Table_Service{
    void newTable(TableRepository tableRepository,DB_Service db_service) throws SQLException;
    void oldTable(String table, DB_Service db_service) throws SQLException;

    TableRepository getTableRepository();
}
