package com.gradprj.erp.web.tableApp.service;

import com.gradprj.erp.web.tableApp.data.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Table_Name_Dupchk_Service implements Table_Control {

    private final TableRepository tableRepository;

    @Autowired
    public Table_Name_Dupchk_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute() throws SQLException {
        String name = tableRepository.getTableName();
        ResultSet rs = db_service.DB_Ex_query("select count(*) cnt from information_schema.tables where TABLE_NAME='" + name + "'");
        while (rs.next()) {
            String result = rs.getString("cnt");
            if (result.equals("0")) {
                return "true";//중복되지 않음
            } else {
                return "false";//중복됨
            }
        }

        return "true";
    }
}

