package com.gradprj.erp.web.tableApp.Service;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.tableApp.DTO.Table;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Table_Get_list_Service extends BaseApp implements Table_Control {
    private final TableRepository tableRepository;

    @Autowired
    public Table_Get_list_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }
    @Override
    public String Execute() throws SQLException {
        //제외할 시스템 테이블 목록
        String[] extable = {"sort_code","sort_lv1","sort_lv2","page_category","page_list"};
        String query ="select * from information_schema.tables where table_schema = 'gradprj'";
        for (String s : extable) {
            query += " and table_name != '" + s + "'";
        }

        ResultSet rs = db_service.DB_Ex_query(query);

        while (rs.next()) {
//            System.out.println("table name : " + rs.getString("TABLE_NAME"));
            Table table = new Table(
                    rs.getString("TABLE_NAME"),
                    rs.getString("TABLE_NAME"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("TABLE_COMMENT")
            );
            tableRepository.save(table);
        }
        return null;
    }
}
