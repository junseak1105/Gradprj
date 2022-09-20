package com.gradprj.erp.web.tableApp.DAO;

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
        ResultSet rs = db_service.DB_Ex_query("select * from information_schema.tables where table_schema = 'gradprj'");
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
