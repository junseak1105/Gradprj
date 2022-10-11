package com.gradprj.erp.web.pageApp.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Page_Get_Tablename implements Page_Service {
    @Override
    public String Execute(String page_name) throws SQLException {
        String sql = "select page_table from page_list\n" +
                "left join page_category pc on pc.page_category = page_list.page_category\n" +
                "where page_name='"+page_name+"';";
        ResultSet rs = db_service.DB_Ex_query(sql);
        rs.next();
        String table_name = rs.getString("page_table");
        return table_name;
    }
}
