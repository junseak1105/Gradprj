package com.gradprj.erp.web.adminApp.DAO;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DTO.Page;
import com.gradprj.erp.web.adminApp.DTO.PageRepository;
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
    public String Execute(String condition) throws SQLException {
        ResultSet rs = db_service.DB_Ex_query("select page_list.idx,page_name,page_desc,page_url, page_table,pc.page_category from page_list left join page_category pc on pc.page_category = page_list.page_category;");
        while (rs.next()) {
            Page page = new Page(
                    Integer.parseInt(rs.getString("idx")),
                    rs.getString("page_name"),
                    rs.getString("page_desc"),
                    rs.getString("page_url"),
                    rs.getString("page_table"),
                    rs.getString("page_category")
            );
            pageRepository.save(page);
        }
        return null;
    }
}
