package com.gradprj.erp.web.pageApp.DAO;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.pageApp.DTO.Datagrid;
import com.gradprj.erp.web.pageApp.DTO.DatagridRepository;
import com.gradprj.erp.web.pageApp.DTO.Page;
import com.gradprj.erp.web.pageApp.DTO.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Page_Get_List_Service extends BaseApp implements Page_Service {

    private final PageRepository pageRepository;

    @Autowired
    public Page_Get_List_Service(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public String Execute() throws SQLException {
        ResultSet rs = db_service.DB_Ex_query("select * from page_list");

        while (rs.next()) {
            Page page = new Page(
                    Integer.parseInt(rs.getString("idx")),
                    rs.getString("page_name"),
                    rs.getString("page_desc"),
                    rs.getString("page_url"),
                    rs.getString("page_table")
            );
            pageRepository.save(page);
        }
        return null;
    }
}
