package com.gradprj.erp.web.tableApp.Service;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.tableApp.DTO.Table;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Table_Get_info_Service extends BaseApp implements Table_Control {
    private final TableRepository tableRepository;

    @Autowired
    public Table_Get_info_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute() throws SQLException {
        String name = tableRepository.getTableName();
        System.out.println(name);
        ResultSet rs = db_service.DB_Ex_query("select * from information_schema.columns where TABLE_NAME='" + name + "'");
        while (rs.next()) {
            Table table = new Table(
                    name,
                    rs.getString("COLUMN_NAME"),
                    rs.getString("COLUMN_TYPE"),
                    rs.getString("IS_NULLABLE"),
                    rs.getString("COLUMN_KEY"),
                    rs.getString("COLUMN_DEFAULT"),
                    rs.getString("EXTRA"),
                    rs.getString("COLUMN_COMMENT")
            );
            tableRepository.save(table);
        }
        return null;
    }
}
